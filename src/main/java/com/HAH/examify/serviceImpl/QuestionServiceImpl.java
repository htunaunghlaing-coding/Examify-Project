package com.HAH.examify.serviceImpl;

import com.HAH.examify.dto.QuestionDto;
import com.HAH.examify.model.Answer;
import com.HAH.examify.model.Exam;
import com.HAH.examify.model.Question;
import com.HAH.examify.repository.ExamRepository;
import com.HAH.examify.repository.QuestionRepository;
import com.HAH.examify.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<QuestionDto> getAllQuestions() {
		return questionRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<QuestionDto> getQuestionById(Long id) {
		return questionRepository.findById(id).map(this::toDto);
	}

	@Override
	public QuestionDto createQuestion(QuestionDto questionDto) {
		Question question = fromDto(questionDto);
		Question savedQuestion = questionRepository.save(question);
		return toDto(savedQuestion);
	}

	@Override
	public QuestionDto updateQuestion(Long id, QuestionDto questionDto) {
		Optional<Question> existingQuestion = questionRepository.findById(id);
		if (existingQuestion.isPresent()) {
			Question question = existingQuestion.get();
			modelMapper.map(questionDto, question);
			Question updatedQuestion = questionRepository.save(question);
			return toDto(updatedQuestion);
		}
		return null;
	}

	@Override
	public void deleteQuestion(Long id) {
		questionRepository.deleteById(id);
	}

	private QuestionDto toDto(Question question) {
		QuestionDto dto = modelMapper.map(question, QuestionDto.class);
		if (question.getExam() != null) {
			dto.setExamId(question.getExam().getId());
		}
		return dto;
	}

	@Override
	public Question fromDto(QuestionDto dto) {
		// Convert QuestionDto to Question entity
		Question question = modelMapper.map(dto, Question.class);

		// Fetch and set the Exam based on the provided Exam ID in the DTO
		if (dto.getExamId() != null) {
			Exam exam = examRepository.findById(dto.getExamId())
					.orElseThrow(() -> new RuntimeException("Exam not found"));
			question.setExam(exam);
		}

		// Map and set Answers if provided
		if (dto.getAnswers() != null && !dto.getAnswers().isEmpty()) {
			List<Answer> answers = dto.getAnswers().stream().map(answerDto -> {
				Answer answer = modelMapper.map(answerDto, Answer.class);
				answer.setQuestion(question); // Set the relationship
				return answer;
			}).collect(Collectors.toList());
			question.setAnswers(answers);
		}

		return question;
	}
}