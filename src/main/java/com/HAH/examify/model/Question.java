package com.HAH.examify.model;

import java.util.List;

import jakarta.persistence.CascadeType;
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

	@ManyToOne
	@JoinColumn(name = "exam_id", nullable = false)
	private Exam exam;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers;

	public Question() {
	}

	public Question(String questionText) {
		this.questionText = questionText;
	}

}
