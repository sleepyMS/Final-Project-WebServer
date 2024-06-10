package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto insertUser(SignUpDto signUpDto);
    List<UserDto> getAllUser();
    boolean isOk(String email, String password);
    UserDto getUserById(int id);
    int validationSignUp(SignUpDto signUpDto);
    void setUserOTP(String email);
    boolean areYouAdmin(String email);
    int changePassword(int id,String currentPassword, String newPassword, String checkNewPassword);
    void changeMbti(int id,String mbti);
    UserDto deleteUser(UserDto userDto);
}
