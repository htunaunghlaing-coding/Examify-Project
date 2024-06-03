package com.HAH.examify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.HAH.examify.dto.QuestionDto;

@Service
public interface QuestionService {

	List<QuestionDto> getAllQuestions();

	Optional<QuestionDto> getQuestionById(Long id);

	QuestionDto createQuestion(QuestionDto questionDto);

	Optional<QuestionDto> updateQuestion(Long id, QuestionDto questionDto);

	boolean deleteQuestion(Long Id);
}
