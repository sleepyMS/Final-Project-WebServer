package com.finalProject.finalProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String birth;
    private String mbti;
    private String nick;
    private String phone;
    private boolean isAdmin;
    private String userOTP;
    private LocalDate currentDate;
}
