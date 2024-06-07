package com.HAH.examify.controller;

import com.HAH.examify.dto.QuestionDto;
import com.HAH.examify.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping
	public List<QuestionDto> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@GetMapping("/{id}")
	public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id) {
		Optional<QuestionDto> question = questionService.getQuestionById(id);
		return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<QuestionDto> createQuestion(@Validated @RequestBody QuestionDto questionDto) {
		QuestionDto createdQuestion = questionService.createQuestion(questionDto);
		return ResponseEntity.ok(createdQuestion);
	}

	@PutMapping("/{id}")
	public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id,
			@Validated @RequestBody QuestionDto questionDto) {
		QuestionDto updatedQuestion = questionService.updateQuestion(id, questionDto);
		return updatedQuestion != null ? ResponseEntity.ok(updatedQuestion) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
		questionService.deleteQuestion(id);
		return ResponseEntity.noContent().build();
	}
}
