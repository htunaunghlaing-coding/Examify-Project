package com.HAH.examify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HAH.examify.model.Exam;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {

}
