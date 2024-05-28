package com.finalProject.finalProject.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
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
