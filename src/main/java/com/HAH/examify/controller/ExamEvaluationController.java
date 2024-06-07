package com.HAH.examify.controller;

import com.HAH.examify.dto.ExamResultDto;
import com.HAH.examify.dto.ExamSubmissionDto;
import com.HAH.examify.service.ExamEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-evaluation")
public class ExamEvaluationController {

	@Autowired
	private ExamEvaluationService examEvaluationService;

	@PostMapping
	public ResponseEntity<ExamResultDto> evaluateExam(@Validated @RequestBody ExamSubmissionDto examSubmissionDto) {
		ExamResultDto examResult = examEvaluationService.evaluateExam(examSubmissionDto);
		return ResponseEntity.ok(examResult);
	}
}
