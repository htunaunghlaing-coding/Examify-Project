package com.HAH.examify.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.HAH.examify.dto.ExamSubmissionDto;
import com.HAH.examify.model.ExamResult;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Specify matching strategy
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		// Explicit mapping for ExamResult.id
		modelMapper.createTypeMap(ExamSubmissionDto.class, ExamResult.class)
				.addMapping(ExamSubmissionDto::getExamId, ExamResult::setExam)
				.addMapping(ExamSubmissionDto::getStudentId, ExamResult::setStudent);

		return modelMapper;
	}
}
