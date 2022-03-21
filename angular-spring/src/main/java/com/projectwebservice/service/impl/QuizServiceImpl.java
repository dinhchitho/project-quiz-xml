package com.projectwebservice.service.impl;

import com.projectwebservice.dto.ListQuiz;
import com.projectwebservice.model.exam.Category;
import com.projectwebservice.model.exam.Quiz;
import com.projectwebservice.repo.QuizRepository;
import com.projectwebservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.projectwebservice.dto.*;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long QuizId) {
        this.quizRepository.deleteById(QuizId);
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepository.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category c) {
        return this.quizRepository.findByCategoryAndActive(c,true);
    }

    //Get data từ file
    @Override
    public ListQuiz unmarshalling(String XmlPath) {
        ListQuiz listQuiz = new ListQuiz();
        try {
            JAXBContext context = JAXBContext.newInstance(ListQuiz.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            listQuiz = ((ListQuiz) unmarshaller.unmarshal(new File(XmlPath)));

        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return listQuiz;
    }

    @Override
    public Quiz importQuizXml() {
        List<com.projectwebservice.dto.Quiz> quizListXml = unmarshalling("file/quiz.xml").getQuizList();
        System.out.println("quiz"+quizListXml);
        return null;
    }
}
