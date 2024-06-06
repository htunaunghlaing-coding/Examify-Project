package com.HAH.examify.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.HAH.examify.dto.ExamDto;
import com.HAH.examify.model.Answer;
import com.HAH.examify.model.Exam;
import com.HAH.examify.model.Question;
import com.HAH.examify.model.Student;
import com.HAH.examify.repository.AnswerRepo;
import com.HAH.examify.repository.ExamRepo;
import com.HAH.examify.repository.QuestionRepo;
import com.HAH.examify.repository.StudentRepo;
import com.HAH.examify.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	private StudentRepo studentRepo;

	private ExamRepo examRepo;

	private QuestionRepo questionRepo;

	private AnswerRepo answerRepo;

	private ModelMapper modelMapper;

	public ExamServiceImpl(StudentRepo studentRepo, ExamRepo examRepo, QuestionRepo questionRepo, AnswerRepo answerRepo,
			ModelMapper modelMapper) {
		this.studentRepo = studentRepo;
		this.examRepo = examRepo;
		this.questionRepo = questionRepo;
		this.answerRepo = answerRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ExamDto> getAllExams() {
		return examRepo.findAll().stream().map(exam -> modelMapper.map(exam, ExamDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ExamDto> getExamById(Long id) {
		return examRepo.findById(id).map(exam -> modelMapper.map(exam, ExamDto.class));
	}

	@Override
	public ExamDto createExam(ExamDto examDTO) {
		Exam exam = modelMapper.map(examDTO, Exam.class);
		Exam savedExam = examRepo.save(exam);
		return modelMapper.map(savedExam, ExamDto.class);
	}

	@Override
	public Optional<ExamDto> updateExam(Long id, ExamDto examDTO) {
		if (!examRepo.existsById(id)) {
			return Optional.empty();
		}
		Exam exam = modelMapper.map(examDTO, Exam.class);
		exam.setId(id);
		Exam updatedExam = examRepo.save(exam);
		return Optional.of(modelMapper.map(updatedExam, ExamDto.class));
	}

	@Override
	public boolean deleteExam(Long id) {
		if (!examRepo.existsById(id)) {
			return false;
		}
		examRepo.deleteById(id);
		return true;
	}

	@Override
	public String evaluateExam(Long studentId, Long examId, List<Long> answerIds) {
		Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Exam exam = examRepo.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found"));

        if (!student.getCourses().contains(exam.getCourse())) {
            throw new IllegalArgumentException("Student is not enrolled in this course");
        }

        int totalQuestions = exam.getQuestions().size();
        int correctAnswers = 0;

        for (int i = 0; i < totalQuestions; i++) {
            Question question = exam.getQuestions().get(i);
            Answer selectedAnswer = answerRepo.findById(answerIds.get(i))
                    .orElseThrow(() -> new IllegalArgumentException("Answer not found"));

            if (question.getAnswers().contains(selectedAnswer) &&
                question.getAnswers().indexOf(selectedAnswer) == question.getCorrectAnswerIndex()) {
                correctAnswers++;
            }
        }

        double score = (double) correctAnswers / totalQuestions * 100;
        if (score >= 75) {
            return "Pass with Distinction";
        } else if (score >= 60) {
            return "Pass with Merit";
        } else if (score >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
	}

}
