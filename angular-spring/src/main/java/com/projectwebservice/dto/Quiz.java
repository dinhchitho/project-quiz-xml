package com.projectwebservice.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "", propOrder = {
        "title",
        "description",
        "maxMarks",
        "numberOfQuestion",
        "category",
        "questions"
})

@XmlRootElement(name = "quiz")
@XmlAccessorType(XmlAccessType.FIELD)
public class Quiz {
    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "maxMarks")
    private String maxMarks;

    @XmlElement(name = "numberOfQuestion")
    private String numberOfQuestion;

    @XmlElement(name = "category")
    private Category category;

    @XmlElement(name = "questions")
    private List<Question> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(String numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", maxMarks='" + maxMarks + '\'' +
                ", numberOfQuestion='" + numberOfQuestion + '\'' +
                ", category=" + category +
                ", questions=" + questions +
                '}';
    }
}
