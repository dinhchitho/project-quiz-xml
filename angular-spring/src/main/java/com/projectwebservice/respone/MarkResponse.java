package com.projectwebservice.respone;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MarkResponse {
    private double marksGot;

    private Integer correctAnswers;

    private Integer attempted;
}
