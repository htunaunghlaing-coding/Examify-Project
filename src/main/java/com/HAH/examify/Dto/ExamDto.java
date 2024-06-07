package com.HAH.examify.dto;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {

	private Long id;

	@NotNull(message = "Title is required")
	private String title;

	@NotNull(message = "Course ID is required")
	private Long courseId;

	private List<QuestionDto> questions;
}
