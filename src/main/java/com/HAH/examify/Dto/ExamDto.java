package com.HAH.examify.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
	
	private Long id;
	private String name;
	private List<QuestionDto> questions;

}
