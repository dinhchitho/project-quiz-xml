package com.projectwebservice.controller;

import com.projectwebservice.model.exam.Question;
import com.projectwebservice.model.exam.Quiz;
import com.projectwebservice.service.QuestionService;
import com.projectwebservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

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
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
        System.out.println(questions);
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
        return ResponseEntity.ok(map);
    }
}
