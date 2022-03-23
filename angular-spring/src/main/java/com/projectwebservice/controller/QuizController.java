package com.projectwebservice.controller;

import com.projectwebservice.model.exam.Category;
import com.projectwebservice.model.exam.Quiz;
import com.projectwebservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity<?> quizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }
    @GetMapping("/{qid}")
    public Quiz quiz(@PathVariable("qid")Long qid)
    {
        return this.quizService.getQuiz(qid);
    }
    @DeleteMapping("/{qid}")
    public void delete(@PathVariable("qid")Long qid){
        this.quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid")Long cid){
        Category category=new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }
    @GetMapping("/category/active/{cid}")
    public List<Quiz> getActiveQuizzes(@PathVariable("cid")Long cid){
        Category category=new Category();
        category.setCid(cid);
        System.out.println("category active:"+this.quizService.getQuizzesOfCategory(category));
        return this.quizService.getActiveQuizzesOfCategory(category);
    }

    @GetMapping("/import")
    public List<Quiz> importXml() {
        return quizService.importQuizXml();
    }
}
