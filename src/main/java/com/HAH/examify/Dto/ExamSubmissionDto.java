package com.HAH.examify.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamSubmissionDto {

	@NotNull(message = "Student ID is required")
	private Long studentId;

	@NotNull(message = "Exam ID is required")
	private Long examId;

	@NotNull(message = "Answers are required")
	private List<AnswerSelectionDto> answers;

}
