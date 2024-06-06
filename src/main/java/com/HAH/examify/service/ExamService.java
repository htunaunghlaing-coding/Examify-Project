package com.HAH.examify.service;

import java.util.List;
import java.util.Optional;

import com.HAH.examify.dto.ExamDto;

public interface ExamService {

	List<ExamDto> getAllExams();

	Optional<ExamDto> getExamById(Long id);

	ExamDto createExam(ExamDto examDTO);

	Optional<ExamDto> updateExam(Long id, ExamDto examDTO);

	boolean deleteExam(Long id);

	String evaluateExam(Long studentId, Long examId, List<Long> answerIds);
}
