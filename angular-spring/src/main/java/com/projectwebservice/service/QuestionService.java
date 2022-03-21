package com.projectwebservice.service;

import com.projectwebservice.model.exam.Question;
import com.projectwebservice.model.exam.Quiz;

import java.util.Set;

public interface QuestionService {
    Question addQuestion(Question question);
    Question updateQuestion(Question question);
    Set<Question> getQuestions();
    Question getQuestion(Long questionId);
    Set<Question> getQuestionOfQuiz(Quiz quiz);
    void deleteQuestion(Long quesId);
    Question get(Long questionId);
}
