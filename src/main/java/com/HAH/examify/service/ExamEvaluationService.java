package com.HAH.examify.service;

import com.HAH.examify.dto.ExamResultDto;
import com.HAH.examify.dto.ExamSubmissionDto;

public interface ExamEvaluationService {

	ExamResultDto evaluateExam(ExamSubmissionDto examSubmissionDto);

}
