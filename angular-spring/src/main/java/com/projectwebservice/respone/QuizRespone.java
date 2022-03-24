package com.projectwebservice.respone;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuizRespone {
    private String title;
    private String description;
    private MarkResponse markResponse;
}
