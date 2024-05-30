package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.SignUpDto;

import java.util.List;

public interface SignUpDao {
    int count();
    SignUpDto getUserById(int id);
    List<SignUpDto> getAllUser();
    SignUpDto insertUser(SignUpDto signUpDto);
    //UserDto getUserById(int id);
}
