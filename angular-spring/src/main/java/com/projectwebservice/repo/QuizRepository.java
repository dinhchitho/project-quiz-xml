package com.projectwebservice.repo;

import com.projectwebservice.model.exam.Category;
import com.projectwebservice.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    List<Quiz> findByCategory(Category category);
    List<Quiz> findByActive(Boolean b);
    List<Quiz> findByCategoryAndActive(Category category,Boolean b);
    @Query(value = "select * from quiz q inner join mark m where m.user_id =:userId and q.q_id = m.quiz_q_id", nativeQuery = true)
    List<Quiz> findAllQuizByUser(Long userId);
}
