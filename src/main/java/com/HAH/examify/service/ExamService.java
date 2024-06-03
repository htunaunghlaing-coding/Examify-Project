package com.HAH.examify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.HAH.examify.dto.ExamDto;

@Service
public interface ExamService {

	List<ExamDto> getAllExams();

	Optional<ExamDto> getExamById(Long id);

	ExamDto createExam(ExamDto examDto);

	Optional<ExamDto> updateExam(Long id, ExamDto examDto);

	boolean deleteExam(Long id);

}
