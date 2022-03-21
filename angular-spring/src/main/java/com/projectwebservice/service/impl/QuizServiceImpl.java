package com.projectwebservice.service.impl;

import com.projectwebservice.dto.ListQuiz;
import com.projectwebservice.model.exam.Category;
import com.projectwebservice.model.exam.Quiz;
import com.projectwebservice.repo.CategoryRepository;
import com.projectwebservice.repo.QuestionRepository;
import com.projectwebservice.repo.QuizRepository;
import com.projectwebservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.*;

import com.projectwebservice.dto.*;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
        System.out.println("quizListXml"+quizListXml);
        for (com.projectwebservice.dto.Quiz quizXml: quizListXml
             ) {
            Quiz quiz = new Quiz();
            Category category = new Category();
            Set<com.projectwebservice.model.exam.Question> questionList = new HashSet<>();
            quiz.setTitle(quizXml.getTitle());
            quiz.setActive(true);
            quiz.setDescription(quizXml.getDescription());
            quiz.setMaxMarks(quizXml.getMaxMarks());
            quiz.setNumberOfQuestion(quizXml.getNumberOfQuestion());
            category.setTitle(quizXml.getCategory().getTitle());
            category.setDescription(quizXml.getCategory().getDescription());
            Category categoryRes = categoryRepository.save(category);
            for (Question questionXml: quizXml.getQuestions()
                 ) {
                com.projectwebservice.model.exam.Question question = new com.projectwebservice.model.exam.Question();
                question.setContent(questionXml.getContent());
                question.setImage(questionXml.getImage());
                question.setOption1(questionXml.getOption1());
                question.setOption2(questionXml.getOption2());
                question.setOption3(questionXml.getOption3());
                question.setOption4(questionXml.getOption4());
                question.setAnswer(questionXml.getAnswer());
                questionList.add(question);
            }
            quiz.setCategory(categoryRes);
            Quiz quizRes = quizRepository.save(quiz);
            for (com.projectwebservice.model.exam.Question question1: questionList
            ) {
                question1.setQuiz(quizRes);
                questionRepository.save(question1);
            }

        }
        return null;
    }
}
