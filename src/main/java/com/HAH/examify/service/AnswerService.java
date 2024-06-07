package com.HAH.examify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.HAH.examify.dto.AnswerDto;

@Service
public interface AnswerService {

	List<AnswerDto> getAllAnswers();

	Optional<AnswerDto> getAnswerById(Long id);

	AnswerDto createAnswer(AnswerDto answerDto);

	AnswerDto updateAnswer(Long id, AnswerDto answerDto);

	void deleteAnswer(Long id);

}
