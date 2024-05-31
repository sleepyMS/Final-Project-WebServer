package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;

import java.util.List;

public interface UserDao {
    int count();
    UserDto getUserById(int id);
    List<UserDto> getAllUser();
    UserDto insertUser(UserDto userDto);
    //UserDto getUserById(int id);
}
