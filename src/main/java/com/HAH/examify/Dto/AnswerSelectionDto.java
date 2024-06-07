package com.HAH.examify.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSelectionDto {

	@NotNull(message = "Question ID is required")
	private Long questionId;

	@NotNull(message = "Selected answer ID is required")
	private Long selectedAnswerId;
}
