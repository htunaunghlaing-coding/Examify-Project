package com.HAH.examify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HAH.examify.model.ExamResult;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
}
