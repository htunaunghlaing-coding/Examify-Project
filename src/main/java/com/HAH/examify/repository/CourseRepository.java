package com.HAH.examify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HAH.examify.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
