package com.HAH.examify.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HAH.examify.dto.ExamDto;
import com.HAH.examify.dto.QuestionDto;
import com.HAH.examify.model.Answer;
import com.HAH.examify.model.Course;
import com.HAH.examify.model.Exam;
import com.HAH.examify.model.Question;
import com.HAH.examify.repository.CourseRepository;
import com.HAH.examify.repository.ExamRepository;
import com.HAH.examify.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ExamDto> getAllExams() {
		return examRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ExamDto> getExamById(Long id) {
		return examRepository.findById(id).map(this::toDto);
	}

	@Override
	public ExamDto createExam(ExamDto examDto) {
		Exam exam = fromDto(examDto);
		Exam savedExam = examRepository.save(exam);
		return toDto(savedExam);
	}

	@Override
	public ExamDto updateExam(Long id, ExamDto examDto) {
		Optional<Exam> existingExam = examRepository.findById(id);
		if (existingExam.isPresent()) {
			Exam exam = existingExam.get();
			modelMapper.map(examDto, exam);
			Exam updatedExam = examRepository.save(exam);
			return toDto(updatedExam);
		}
		return null;
	}

	@Override
	public void deleteExam(Long id) {
		examRepository.deleteById(id);
	}

	private ExamDto toDto(Exam exam) {
		ExamDto examDto = modelMapper.map(exam, ExamDto.class);
		if (exam.getCourse() != null) {
			examDto.setCourseId(exam.getCourse().getId());
		}
		return examDto;
	}

	private Exam fromDto(ExamDto examDto) {
		Exam exam = modelMapper.map(examDto, Exam.class);

		if (examDto.getCourseId() != null) {
			Course course = courseRepository.findById(examDto.getCourseId())
					.orElseThrow(() -> new RuntimeException("Course not found"));
			exam.setCourse(course);
		}

		if (examDto.getQuestions() != null && !examDto.getQuestions().isEmpty()) {
			List<Question> questions = examDto.getQuestions().stream().map(this::mapQuestionDtoToEntity)
					.collect(Collectors.toList());
			exam.setQuestions(questions);
		}

		return exam;
	}

	private Question mapQuestionDtoToEntity(QuestionDto questionDto) {
		Question question = modelMapper.map(questionDto, Question.class);
		question.setExam(null); // The exam will be set later in fromDto method

		if (questionDto.getAnswers() != null && !questionDto.getAnswers().isEmpty()) {
			List<Answer> answers = questionDto.getAnswers().stream().map(answerDto -> {
				Answer answer = modelMapper.map(answerDto, Answer.class);
				answer.setQuestion(question);
				return answer;
			}).collect(Collectors.toList());
			question.setAnswers(answers);
		}
		return question;
	}

}
