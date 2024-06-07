package com.HAH.examify.serviceImpl;

import com.HAH.examify.dto.CourseDto;
import com.HAH.examify.model.Course;
import com.HAH.examify.model.Student;
import com.HAH.examify.repository.CourseRepository;
import com.HAH.examify.repository.StudentRepository;
import com.HAH.examify.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CourseDto> getAllCourses() {
		return courseRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<CourseDto> getCourseById(Long id) {
		return courseRepository.findById(id).map(this::toDto);
	}

	@Override
	public CourseDto createCourse(CourseDto courseDto) {
		Course course = fromDto(courseDto);
		return toDto(courseRepository.save(course));
	}

	@Override
	public CourseDto updateCourse(Long id, CourseDto courseDto) {
		Optional<Course> existingCourse = courseRepository.findById(id);
		if (existingCourse.isPresent()) {
			Course course = existingCourse.get();
			course.setName(courseDto.getName());
			return toDto(courseRepository.save(course));
		}
		return null;
	}

	@Override
	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}

	private CourseDto toDto(Course course) {
		return modelMapper.map(course, CourseDto.class);
	}

	private Course fromDto(CourseDto dto) {
		return modelMapper.map(dto, Course.class);
	}

	@Override
	public boolean enrollStudentInCourse(Long courseId, Long studentId) {
		Optional<Course> courseOptional = courseRepository.findById(courseId);
		if (!courseOptional.isPresent()) {
			return false; // Course not found
		}

		Optional<Student> studentOptional = studentRepository.findById(studentId);
		if (!studentOptional.isPresent()) {
			return false; // Student not found
		}

		Course course = courseOptional.get();
		Student student = studentOptional.get();

		// Check if student is already enrolled in the course
		if (course.getStudents().contains(student)) {
			return false; // Student is already enrolled in the course
		}

		// Add student to the course
		course.getStudents().add(student);

		// Save the updated course
		courseRepository.save(course);

		return true; // Student enrolled successfully
	}
}
