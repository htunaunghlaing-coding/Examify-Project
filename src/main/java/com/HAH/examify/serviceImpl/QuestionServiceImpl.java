package com.HAH.examify.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HAH.examify.dto.QuestionDto;
import com.HAH.examify.model.Question;
import com.HAH.examify.repository.QuestionRepo;
import com.HAH.examify.service.QuestionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepo questionRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<QuestionDto> getAllQuestions() {
		return questionRepo.findAll().stream().map(question -> modelMapper.map(question, QuestionDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<QuestionDto> getQuestionById(Long id) {
		return questionRepo.findById(id).map(question -> modelMapper.map(question, QuestionDto.class));
	}

	@Override
	public QuestionDto createQuestion(QuestionDto questionDTO) {
		Question question = modelMapper.map(questionDTO, Question.class);
		Question savedQuestion = questionRepo.save(question);
		return modelMapper.map(savedQuestion, QuestionDto.class);
	}

	@Override
	public Optional<QuestionDto> updateQuestion(Long id, QuestionDto questionDTO) {
		if (!questionRepo.existsById(id)) {
			return Optional.empty();
		}
		Question question = modelMapper.map(questionDTO, Question.class);
		question.setId(id);
		Question updatedQuestion = questionRepo.save(question);
		return Optional.of(modelMapper.map(updatedQuestion, QuestionDto.class));
	}

	@Override
	public boolean deleteQuestion(Long id) {
		if (!questionRepo.existsById(id)) {
			return false;
		}
		questionRepo.deleteById(id);
		return true;
	}
}
