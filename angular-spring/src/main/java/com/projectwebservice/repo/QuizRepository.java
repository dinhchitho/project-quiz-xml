package com.projectwebservice.repo;

import com.projectwebservice.model.exam.Category;
import com.projectwebservice.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    List<Quiz> findByCategory(Category category);
    List<Quiz> findByActive(Boolean b);
    List<Quiz> findByCategoryAndActive(Category category,Boolean b);
}
