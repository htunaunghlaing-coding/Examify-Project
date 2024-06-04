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
public class StudentExamResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int score;

	@ManyToOne
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "exam_id", referencedColumnName = "id")
	private Exam exam;

	public StudentExamResult() {
	}

	public StudentExamResult(Long id, int score) {
		this.id = id;
		this.score = score;
	}

}
