package com.HAH.examify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String answerText;

	@ManyToOne
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question question;

	public Answer() {
	}

	public Answer(String answerText) {
		this.answerText = answerText;
	}

}
