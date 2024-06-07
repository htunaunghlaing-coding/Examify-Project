package com.HAH.examify.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HAH.examify.dto.StudentDto;
import com.HAH.examify.model.Student;
import com.HAH.examify.repository.StudentRepository;
import com.HAH.examify.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<StudentDto> getAllStudents() {
		return studentRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<StudentDto> getStudentById(Long id) {
		return studentRepository.findById(id).map(this::toDto);
	}

	@Override
	public StudentDto createStudent(StudentDto studentDto) {
		Student student = fromDto(studentDto);
		return toDto(studentRepository.save(student));
	}

	@Override
	public StudentDto updateStudent(Long id, StudentDto studentDto) {
		Optional<Student> existingStudent = studentRepository.findById(id);
		if (existingStudent.isPresent()) {
			Student student = existingStudent.get();
			student.setName(studentDto.getName());
			return toDto(studentRepository.save(student));
		}
		return null;
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

	private StudentDto toDto(Student student) {
		StudentDto dto = new StudentDto();
		dto.setId(student.getId());
		dto.setName(student.getName());
		return dto;
	}

	private Student fromDto(StudentDto dto) {
		Student student = new Student();
		student.setName(dto.getName());
		return student;
	}
}
