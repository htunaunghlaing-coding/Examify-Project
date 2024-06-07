package com.HAH.examify.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HAH.examify.dto.CourseDto;
import com.HAH.examify.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping
	public List<CourseDto> getAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
		Optional<CourseDto> course = courseService.getCourseById(id);
		return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<CourseDto> createCourse(@Validated @RequestBody CourseDto courseDto) {
		CourseDto createdCourse = courseService.createCourse(courseDto);
		return ResponseEntity.ok(createdCourse);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseDto> updateCourse(@PathVariable Long id, @Validated @RequestBody CourseDto courseDto) {
		CourseDto updatedCourse = courseService.updateCourse(id, courseDto);
		return updatedCourse != null ? ResponseEntity.ok(updatedCourse) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{courseId}/enroll/{studentId}")
	public ResponseEntity<String> enrollStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
		boolean success = courseService.enrollStudentInCourse(courseId, studentId);
		if (success) {
			return ResponseEntity.ok("Student enrolled in course successfully.");
		} else {
			return ResponseEntity.badRequest().body("Failed to enroll student in course.");
		}
	}
}
