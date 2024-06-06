package com.HAH.examify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.HAH.examify.dto.QuestionDto;
import com.HAH.examify.service.QuestionService;

import java.util.List;

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
		return ResponseEntity.of(questionService.getQuestionById(id));
	}

	@PostMapping
	public QuestionDto createQuestion(@RequestBody QuestionDto QuestionDto) {
		return questionService.createQuestion(QuestionDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto QuestionDto) {
		return ResponseEntity.of(questionService.updateQuestion(id, QuestionDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
		if (questionService.deleteQuestion(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
