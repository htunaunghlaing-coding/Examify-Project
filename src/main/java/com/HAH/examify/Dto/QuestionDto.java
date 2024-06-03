package com.HAH.examify.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
	
	private Long id;
	private String questionText;
	private int correctAnswerIndex;
	private List<AnswerDto> answers;

}
