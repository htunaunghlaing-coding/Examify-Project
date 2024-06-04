package com.HAH.examify.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

	private Long id;
	@NotEmpty(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
	private String nam;
	private List<StudentDto> studentDtos;
	private ExamDto exam;

}
