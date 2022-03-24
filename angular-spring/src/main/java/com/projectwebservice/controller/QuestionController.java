package com.projectwebservice.controller;

import com.projectwebservice.model.User;
import com.projectwebservice.model.exam.Mark;
import com.projectwebservice.model.exam.Question;
import com.projectwebservice.model.exam.Quiz;
import com.projectwebservice.service.MarkService;
import com.projectwebservice.service.QuestionService;
import com.projectwebservice.service.QuizService;
import com.projectwebservice.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private MarkService markService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid")Long qid){
//        Quiz quiz=new Quiz();
//        quiz.setQId(qid);
//        Set<Question> questionsOfQuiz=this.questionService.getQuestionOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);
        Quiz quiz=this.quizService.getQuiz(qid);
        Set<Question> questions=quiz.getQuestionSet();
        List<Question> list=new ArrayList(questions);
        if (list.size()>Integer.parseInt(quiz.getNumberOfQuestion())){
            list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestion()+1));
        }
        list.forEach((q)->{
            q.setAnswer("");
        });
        System.out.println("i love you");
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid")Long qid){
        Quiz quiz=new Quiz();
        quiz.setQId(qid);
        Set<Question> questionsOfQuiz=this.questionService.getQuestionOfQuiz(quiz);
        return ResponseEntity.ok(questionsOfQuiz);
    }

    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId")Long quesId){
        return this.questionService.getQuestion(quesId);
    }
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId")Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions, Principal principal){
        System.out.println(questions);
        User user = (User) this.userDetailsService.loadUserByUsername(principal.getName());

        Quiz quiz =  questions.get(0).getQuiz();
        double markGot=0;
        Integer correctAnswers=0;
        Integer attempted=0;
        for (Question q:questions){
            Question question=this.questionService.get(q.getQuesId());
            if (question.getAnswer().equals(q.getGivenAnswer())){
                correctAnswers++;
                double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                markGot+=marksSingle;
            }
            if (q.getGivenAnswer().trim() != ""){
                attempted++;
            }
        };
        double finalMarkGot = markGot;
        Integer finalCorrectAnswers = correctAnswers;
        Integer finalAttempted = attempted;
        Map<String,Object> map=new HashMap<String,Object>(){{
            put("marksGot", finalMarkGot);
            put("correctAnswers", finalCorrectAnswers);
            put("attempted", finalAttempted);
        }
        };
        Mark mark = new Mark();
        mark.setMarksGot(finalMarkGot);
        mark.setCorrectAnswers(finalCorrectAnswers);
        mark.setAttempted(finalAttempted);
        mark.setQuiz(quiz);
        mark.setUser(user);
        Mark result = markService.addMark(mark);
        return ResponseEntity.ok(map);
    }
}
