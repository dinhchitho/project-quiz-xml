package com.projectwebservice.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "", propOrder = {
})

@XmlRootElement(name = "list_quiz")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListQuiz {

    @XmlElement(name = "quiz")
    private List<Quiz> quizList;

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public String toString() {
        return "ListQuiz{" +
                "quizList=" + quizList +
                '}';
    }
}
