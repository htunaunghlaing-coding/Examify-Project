package com.HAH.examify.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

	private Long id;
	@NotEmpty(message = "Question text is required")
	@Size(min = 10, max = 1000, message = "Question text should be between 10 and 1000 characters")
	private String questionText;
	@NotNull(message = "Correct answer index is required")
	private int correctAnswerIndex;

}
