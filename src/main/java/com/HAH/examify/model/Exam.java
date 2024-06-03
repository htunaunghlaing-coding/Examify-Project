package com.HAH.examify.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToOne
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;

	@OneToMany(mappedBy = "exam")
	private List<Question> questions;

	public Exam() {
	}

	public Exam(String name) {
		this.name = name;
	}

}
