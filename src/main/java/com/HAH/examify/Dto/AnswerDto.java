package com.HAH.examify.dto;

import jakarta.validation.constraints.NotEmpty;

public class AnswerDto {

	private Long id;

	@NotEmpty(message = "Answer text cannot be empty")
	private String text;

	private boolean isCorrect;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}