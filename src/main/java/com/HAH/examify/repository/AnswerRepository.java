package com.HAH.examify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HAH.examify.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
