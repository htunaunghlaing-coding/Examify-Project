package com.HAH.examify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultDto {

	private Long id;

	private int score;

	private String result;

	private Long studentId;

	private Long examId;
}
