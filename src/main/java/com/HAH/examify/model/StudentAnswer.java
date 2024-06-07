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
public class StudentAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	@ManyToOne
	@JoinColumn(name = "answer_id", nullable = false)
	private Answer answer;

	public StudentAnswer() {
	}

	public StudentAnswer(Student student, Question question, Answer answer) {
		this.student = student;
		this.question = question;
		this.answer = answer;
	}

}
