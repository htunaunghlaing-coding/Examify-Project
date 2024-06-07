package com.HAH.examify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.HAH.examify.dto.QuestionDto;
import com.HAH.examify.model.Question;

@Service
public interface QuestionService {

	List<QuestionDto> getAllQuestions();

	Optional<QuestionDto> getQuestionById(Long id);

	QuestionDto createQuestion(QuestionDto questionDto);

	QuestionDto updateQuestion(Long id, QuestionDto questionDto);

	void deleteQuestion(Long id);

	Question fromDto(QuestionDto dto);
}
