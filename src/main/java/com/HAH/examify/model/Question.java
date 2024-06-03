package com.HAH.examify.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String questionText;
	private int correctAnswerIndex;

	@ManyToOne
	@JoinColumn(name = "exam_id", referencedColumnName = "id")
	private Exam exam;

	@OneToMany(mappedBy = "question")
	private List<Answer> answers;

	public Question() {
	}

	public Question(String questionText, int correctAnswerIndex) {
		this.questionText = questionText;
		this.correctAnswerIndex = correctAnswerIndex;
	}

}
