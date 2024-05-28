package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.LoginDto;
import com.finalProject.finalProject.dto.SignUpDto;

import java.util.List;

public interface SignUpService {
    SignUpDto insertUser(SignUpDto signUpDto, int id);
    List<SignUpDto> getAllUser();
    boolean isOk(String email, String password);
    SignUpDto getUserById(int id);
}
