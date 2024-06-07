package com.HAH.examify.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HAH.examify.dto.ExamDto;
import com.HAH.examify.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

	@Autowired
	private ExamService examService;

	@GetMapping
	public List<ExamDto> getAllExams() {
		return examService.getAllExams();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExamDto> getExamById(@PathVariable Long id) {
		Optional<ExamDto> exam = examService.getExamById(id);
		return exam.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ExamDto> createExam(@Validated @RequestBody ExamDto examDto) {
		ExamDto createdExam = examService.createExam(examDto);
		return ResponseEntity.ok(createdExam);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ExamDto> updateExam(@PathVariable Long id, @Validated @RequestBody ExamDto examDto) {
		ExamDto updatedExam = examService.updateExam(id, examDto);
		return updatedExam != null ? ResponseEntity.ok(updatedExam) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
		examService.deleteExam(id);
		return ResponseEntity.noContent().build();
	}
}