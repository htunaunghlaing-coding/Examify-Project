package com.HAH.examify.dto;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

	private Long id;

	@NotNull(message = "Name is required")
	private String name;

	private List<Long> studentIds;
}
