package com.HAH.examify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.HAH.examify.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
