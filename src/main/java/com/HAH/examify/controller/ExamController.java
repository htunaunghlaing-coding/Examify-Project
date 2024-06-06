package com.HAH.examify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HAH.examify.dto.ExamDto;
import com.HAH.examify.service.ExamService;

import java.util.List;

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
		return ResponseEntity.of(examService.getExamById(id));
	}

	@PostMapping
	public ExamDto createExam(@RequestBody ExamDto ExamDto) {
		return examService.createExam(ExamDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ExamDto> updateExam(@PathVariable Long id, @RequestBody ExamDto ExamDto) {
		return ResponseEntity.of(examService.updateExam(id, ExamDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
		if (examService.deleteExam(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/{id}/evaluate")
	public ResponseEntity<String> evaluateExam(@PathVariable Long id, @RequestParam Long studentId,
			@RequestBody List<Long> answerIds) {
		return ResponseEntity.ok(examService.evaluateExam(studentId, id, answerIds));
	}
}
