package com.HAH.examify.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "courses")
	private List<Student> students;

	@OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
	private Exam exam;

	public Course() {
	}

	public Course(String name) {
		this.name = name;
	}

}
