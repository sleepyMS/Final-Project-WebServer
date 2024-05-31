package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto insertUser(SignUpDto signUpDto);
    List<UserDto> getAllUser();
    boolean isOk(String email, String password);
    UserDto getUserById(int id);
}
