package com.HAH.examify.controller;

import com.HAH.examify.dto.AnswerDto;
import com.HAH.examify.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

	@Autowired
	private AnswerService answerService;

	@GetMapping
	public List<AnswerDto> getAllAnswers() {
		return answerService.getAllAnswers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AnswerDto> getAnswerById(@PathVariable Long id) {
		Optional<AnswerDto> answer = answerService.getAnswerById(id);
		return answer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<AnswerDto> createAnswer(@Validated @RequestBody AnswerDto answerDto) {
		AnswerDto createdAnswer = answerService.createAnswer(answerDto);
		return ResponseEntity.ok(createdAnswer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AnswerDto> updateAnswer(@PathVariable Long id, @Validated @RequestBody AnswerDto answerDto) {
		AnswerDto updatedAnswer = answerService.updateAnswer(id, answerDto);
		return updatedAnswer != null ? ResponseEntity.ok(updatedAnswer) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
		answerService.deleteAnswer(id);
		return ResponseEntity.noContent().build();
	}
}
