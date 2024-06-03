package com.HAH.examify.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
	
	private Long id;
	private String nam;
	private List<StudentDto> studentDtos;
	private ExamDto exam;

}
