package com.projectwebservice.controller;

import com.projectwebservice.markxml.ListUser;
import com.projectwebservice.service.MarkService;
import com.projectwebservice.service.QuestionService;
import com.projectwebservice.service.QuizService;
import com.projectwebservice.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.error.Mark;

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
    public ListUser getAllMark() {
        ListUser userList = markService.getAllMark();
        return markService.marshalling(userList);
    }
}
