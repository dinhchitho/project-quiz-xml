package com.projectwebservice.markxml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "", propOrder = {
        "title",
        "description",
        "markQuiz"
})

@XmlRootElement(name = "quiz")
@XmlAccessorType(XmlAccessType.FIELD)
public class QuizMark {
    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "mark")
    private MarkQuiz markQuiz;

    public MarkQuiz getMarkQuiz() {
        return markQuiz;
    }

    public void setMarkQuiz(MarkQuiz markQuiz) {
        this.markQuiz = markQuiz;
    }

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

    @Override
    public String toString() {
        return "QuizMark{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", markQuiz=" + markQuiz +
                '}';
    }
}
