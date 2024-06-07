package com.HAH.examify.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.HAH.examify.dto.CourseDto;

@Service
public interface CourseService {

	List<CourseDto> getAllCourses();

	Optional<CourseDto> getCourseById(Long id);

	CourseDto createCourse(CourseDto courseDto);

	CourseDto updateCourse(Long id, CourseDto courseDto);

	void deleteCourse(Long id);

	boolean enrollStudentInCourse(Long courseId, Long studentId);

}
