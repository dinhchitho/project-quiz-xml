package com.projectwebservice.model.exam;

import com.projectwebservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long markId;

    private double marksGot;

    private Integer correctAnswers;

    private Integer attempted;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    @Override
    public String toString() {
        return "Mark{" +
                "markId=" + markId +
                ", user=" + user +
                ", quiz=" + quiz +
                '}';
    }
}
