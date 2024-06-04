package com.HAH.examify.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

	private Long id;
	@NotEmpty(message = "Answer text is required")
	@Size(min = 1, max = 500, message = "Answer text should be between 1 and 500 characters")
	private String answerText;
}
