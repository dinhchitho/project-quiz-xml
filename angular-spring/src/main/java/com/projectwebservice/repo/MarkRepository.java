package com.projectwebservice.repo;

import com.projectwebservice.model.exam.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    @Query(value = "select * from  mark m inner join user u where m.quiz_q_id=:quizId and u.id=:userId", nativeQuery = true)
    List<Mark> findMarkByQuizId(Long quizId, Long userId);

    @Query(value = "select * from  mark m where m.user_id=:userId", nativeQuery = true)
    List<Mark> findMarkByUserId(Long userId);
}
