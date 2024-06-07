package com.HAH.examify.service;

import java.util.List;
import java.util.Optional;

import com.HAH.examify.dto.ExamDto;

public interface ExamService {

	List<ExamDto> getAllExams();

	Optional<ExamDto> getExamById(Long id);

	ExamDto createExam(ExamDto examDto);

	ExamDto updateExam(Long id, ExamDto examDto);

	void deleteExam(Long id);
}
