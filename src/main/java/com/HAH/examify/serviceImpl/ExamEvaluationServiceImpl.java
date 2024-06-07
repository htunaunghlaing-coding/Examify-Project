package com.HAH.examify.serviceImpl;

import com.HAH.examify.dto.AnswerSelectionDto;
import com.HAH.examify.dto.ExamResultDto;
import com.HAH.examify.dto.ExamSubmissionDto;
import com.HAH.examify.model.Answer;
import com.HAH.examify.model.Exam;
import com.HAH.examify.model.ExamResult;
import com.HAH.examify.model.Question;
import com.HAH.examify.model.Student;
import com.HAH.examify.model.StudentAnswer;
import com.HAH.examify.repository.AnswerRepository;
import com.HAH.examify.repository.ExamRepository;
import com.HAH.examify.repository.ExamResultRepository;
import com.HAH.examify.repository.QuestionRepository;
import com.HAH.examify.repository.StudentRepository;
import com.HAH.examify.service.ExamEvaluationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamEvaluationServiceImpl implements ExamEvaluationService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private ExamResultRepository examResultRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ExamResultDto evaluateExam(ExamSubmissionDto examSubmissionDto) {
		Student student = studentRepository.findById(examSubmissionDto.getStudentId())
				.orElseThrow(() -> new RuntimeException("Student not found"));
		Exam exam = examRepository.findById(examSubmissionDto.getExamId())
				.orElseThrow(() -> new RuntimeException("Exam not found"));

		int totalQuestions = exam.getQuestions().size();
		int correctAnswers = 0;

		for (AnswerSelectionDto answerSelection : examSubmissionDto.getAnswers()) {
			Question question = questionRepository.findById(answerSelection.getQuestionId())
					.orElseThrow(() -> new RuntimeException("Question not found"));
			Answer selectedAnswer = answerRepository.findById(answerSelection.getSelectedAnswerId())
					.orElseThrow(() -> new RuntimeException("Answer not found"));

			if (selectedAnswer.isCorrect()) {
				correctAnswers++;
			}

			// Save student answer
			StudentAnswer studentAnswer = modelMapper.map(answerSelection, StudentAnswer.class);
			studentAnswer.setStudent(student);
			studentAnswer.setQuestion(question);
			studentAnswer.setAnswer(selectedAnswer);
		}

		int score = (correctAnswers * 100) / totalQuestions;
		String result;
		if (score >= 75) {
			result = "DESTINATION";
		} else if (score >= 65) {
			result = "MERIT";
		} else if (score >= 40) {
			result = "PASS";
		} else {
			result = "FAIL";
		}

		// Save exam result
		ExamResult examResult = modelMapper.map(examSubmissionDto, ExamResult.class);
		examResult.setScore(score);
		examResult.setResult(result);
		examResult.setStudent(student);
		examResult.setExam(exam);
		examResult = examResultRepository.save(examResult);

		return modelMapper.map(examResult, ExamResultDto.class);
	}
}
