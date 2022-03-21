package com.projectwebservice.repo;

import com.projectwebservice.model.exam.Question;
import com.projectwebservice.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
