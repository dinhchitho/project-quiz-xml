package com.projectwebservice.markxml;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "", propOrder = {
        "username",
        "email",
        "phone",
        "quizMarkList"
})

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserMark {

    @XmlElement(name = "username")
    private String username;
    @XmlElement(name = "email")
    private String email;
    @XmlElement(name = "phone")
    private String phone;

    @XmlElement(name = "quiz")
    private List<QuizMark> quizMarkList;


    public List<QuizMark> getQuizMarkList() {
        return quizMarkList;
    }

    public void setQuizMarkList(List<QuizMark> quizMarkList) {
        this.quizMarkList = quizMarkList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserMark{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", quizMarkList=" + quizMarkList +
                '}';
    }
}
