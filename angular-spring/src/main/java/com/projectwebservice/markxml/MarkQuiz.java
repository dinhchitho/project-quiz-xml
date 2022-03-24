package com.projectwebservice.markxml;

import javax.xml.bind.annotation.*;

@XmlType(name = "", propOrder = {
        "marksGot",
        "correctAnswers",
        "attempted"
})

@XmlRootElement(name = "mark")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarkQuiz {
    @XmlElement(name = "marksGot")
    private double marksGot;

    @XmlElement(name = "correctAnswers")
    private Integer correctAnswers;

    @XmlElement(name = "attempted")
    private Integer attempted;

    public double getMarksGot() {
        return marksGot;
    }

    public void setMarksGot(double marksGot) {
        this.marksGot = marksGot;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getAttempted() {
        return attempted;
    }

    public void setAttempted(Integer attempted) {
        this.attempted = attempted;
    }

    @Override
    public String toString() {
        return "MarkQuiz{" +
                "marksGot=" + marksGot +
                ", correctAnswers=" + correctAnswers +
                ", attempted=" + attempted +
                '}';
    }
}
