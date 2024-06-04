package com.finalProject.finalProject.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String checkPassword;
    private String birth;
    private String mbti;
    private String nick;
    private String phone;
}
