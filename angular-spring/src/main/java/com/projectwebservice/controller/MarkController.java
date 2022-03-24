package com.projectwebservice.controller;

import com.projectwebservice.markxml.ListUser;
import com.projectwebservice.model.exam.Question;
import com.projectwebservice.respone.UserResponse;
import com.projectwebservice.service.MarkService;
import com.projectwebservice.service.QuestionService;
import com.projectwebservice.service.QuizService;
import com.projectwebservice.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.net.URL;

import java.io.*;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/mark")
@CrossOrigin("*")
public class MarkController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private MarkService markService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/")
    public ResponseEntity<List<UserResponse>> getAllMark() throws IOException {;
        ListUser userList = markService.getAllMark();
        ListUser listUser = markService.marshalling(userList);
        List<UserResponse> userResponses = markService.getAllMarkUser();
        return ResponseEntity.ok(userResponses);
    }
}
