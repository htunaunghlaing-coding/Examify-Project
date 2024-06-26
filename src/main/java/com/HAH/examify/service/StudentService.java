package com.HAH.examify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.HAH.examify.dto.StudentDto;

@Service
public interface StudentService {

	List<StudentDto> getAllStudents();

	Optional<StudentDto> getStudentById(Long id);

	StudentDto createStudent(StudentDto studentDto);

	StudentDto updateStudent(Long id, StudentDto studentDto);

	void deleteStudent(Long id);
}
