package com.HAH.examify.serviceImpl;

import com.HAH.examify.dto.AnswerDto;
import com.HAH.examify.model.Answer;
import com.HAH.examify.repository.AnswerRepository;
import com.HAH.examify.service.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AnswerDto> getAllAnswers() {
		return answerRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<AnswerDto> getAnswerById(Long id) {
		return answerRepository.findById(id).map(this::toDto);
	}

	@Override
	public AnswerDto createAnswer(AnswerDto answerDto) {
		Answer answer = fromDto(answerDto);
		return toDto(answerRepository.save(answer));
	}

	@Override
	public AnswerDto updateAnswer(Long id, AnswerDto answerDto) {
		Optional<Answer> existingAnswer = answerRepository.findById(id);
		if (existingAnswer.isPresent()) {
			Answer answer = existingAnswer.get();
			answer.setText(answerDto.getText());
			answer.setCorrect(answerDto.isCorrect());
			return toDto(answerRepository.save(answer));
		}
		return null;
	}

	@Override
	public void deleteAnswer(Long id) {
		answerRepository.deleteById(id);
	}

	private AnswerDto toDto(Answer answer) {
		return modelMapper.map(answer, AnswerDto.class);
	}

	private Answer fromDto(AnswerDto dto) {
		return modelMapper.map(dto, Answer.class);
	}
}
