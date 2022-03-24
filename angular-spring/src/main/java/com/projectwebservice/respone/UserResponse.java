package com.projectwebservice.respone;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private String username;
    private String phone;
    private String email;
    private List<QuizRespone> quizRespones;
}
