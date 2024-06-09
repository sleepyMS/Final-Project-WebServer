package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;

import java.util.List;

public interface UserDao {
    int count();
    String[] getUserEmail();
    String[] getUserNick();
    int getMaxId();
    UserDto getUserById(int id);
    UserDto getUserByEmail(String email);
    List<UserDto> getAllUser();
    UserDto insertUser(UserDto userDto);
    //UserDto getUserById(int id);
}
