package com.projectwebservice.service;

import com.projectwebservice.dto.ListQuiz;
import com.projectwebservice.model.exam.Category;
import com.projectwebservice.model.exam.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {
    Quiz addQuiz(Quiz quiz);
    Quiz updateQuiz(Quiz quiz);
    Set<Quiz> getQuizzes();
    Quiz getQuiz(Long quizId);
    void deleteQuiz(Long QuizId);

    List<Quiz> getQuizzesOfCategory(Category category);
    List<Quiz> getActiveQuizzes();
    List<Quiz> getActiveQuizzesOfCategory(Category c);
    ListQuiz unmarshalling(String XmlPath);

    Quiz importQuizXml();
}
