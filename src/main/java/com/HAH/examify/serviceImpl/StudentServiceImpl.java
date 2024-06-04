package com.HAH.examify.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HAH.examify.dto.StudentDto;
import com.HAH.examify.model.Student;
import com.HAH.examify.repository.CourseRepo;
import com.HAH.examify.repository.StudentRepo;
import com.HAH.examify.service.StudentService;

import jakarta.validation.Valid;

@Service
public class StudentServiceImpl implements StudentService {

	private CourseRepo courseRepo;

	private StudentRepo studentRepo;

	@Autowired
	private ModelMapper modelMapper;

	public StudentServiceImpl(CourseRepo courseRepo, StudentRepo studentRepo) {

		this.courseRepo = courseRepo;
		this.studentRepo = studentRepo;
	}

	@Override
	public List<StudentDto> getAllStudents() {
		return studentRepo.findAll().stream().map(student -> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<StudentDto> getStudentById(Long id) {
		return studentRepo.findById(id).map(student -> modelMapper.map(student, StudentDto.class));
	}

	@Override
	public StudentDto createStudent(@Valid StudentDto studentDto) {
		Student student = modelMapper.map(studentDto, Student.class);
		Student savedStudent = studentRepo.save(student);
		return modelMapper.map(savedStudent, StudentDto.class);
	}

	@Override
	public Optional<StudentDto> updateStudent(Long id, @Valid StudentDto studentDto) {
		if (!studentRepo.existsById(id)) {
			return Optional.empty();
		}
		Student student = modelMapper.map(studentDto, Student.class);
		student.setId(id);
		Student updatedStudent = studentRepo.save(student);
		return Optional.of(modelMapper.map(updatedStudent, StudentDto.class));
	}

	@Override
	public boolean deleteStudent(Long id) {
		if (!studentRepo.existsById(id)) {
			return false;
		}
		studentRepo.deleteById(id);
		return true;
	}
}
