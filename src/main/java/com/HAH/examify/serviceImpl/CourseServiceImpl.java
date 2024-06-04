package com.HAH.examify.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HAH.examify.dto.CourseDto;
import com.HAH.examify.model.Course;
import com.HAH.examify.repository.CourseRepo;
import com.HAH.examify.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepo courseRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CourseDto> getAllCourses() {
		List<Course> courses = courseRepository.findAll();
		return courses.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public Optional<CourseDto> getCourseById(Long id) {
		Optional<Course> courseOptional = courseRepository.findById(id);
		return courseOptional.map(this::convertToDto);
	}

	@Override
	public CourseDto createCourse(CourseDto courseDto) {
		Course course = convertToEntity(courseDto);
		Course savedCourse = courseRepository.save(course);
		return convertToDto(savedCourse);
	}

	@Override
	public Optional<CourseDto> updateCourse(Long id, CourseDto courseDto) {
		Optional<Course> existingCourseOptional = courseRepository.findById(id);
		if (existingCourseOptional.isPresent()) {
			Course existingCourse = existingCourseOptional.get();
			Course updatedCourse = convertToEntity(courseDto);
			updatedCourse.setId(existingCourse.getId());
			Course savedCourse = courseRepository.save(updatedCourse);
			return Optional.of(convertToDto(savedCourse));
		}
		return Optional.empty();
	}

	@Override
	public boolean deleteCourse(Long id) {
		if (courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
			return true;
		}
		return false;
	}

	private CourseDto convertToDto(Course course) {
		return modelMapper.map(course, CourseDto.class);
	}

	private Course convertToEntity(CourseDto courseDto) {
		return modelMapper.map(courseDto, Course.class);
	}
}
