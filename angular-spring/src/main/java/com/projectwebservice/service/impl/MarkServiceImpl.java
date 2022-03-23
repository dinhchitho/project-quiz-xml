package com.projectwebservice.service.impl;

import com.projectwebservice.model.exam.Mark;
import com.projectwebservice.repo.MarkRepository;
import com.projectwebservice.repo.QuestionRepository;
import com.projectwebservice.repo.QuizRepository;
import com.projectwebservice.service.MarkService;
import com.projectwebservice.service.QuestionService;
import com.projectwebservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MarkRepository markRepository;

    @Override
    public Mark addMark(Mark mark) {
        return markRepository.save(mark);
    }
}
