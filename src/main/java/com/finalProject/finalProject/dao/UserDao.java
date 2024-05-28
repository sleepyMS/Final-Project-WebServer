package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    int count();
    UserDto getUserById(int id);
    List<UserDto> getUser();
    UserDto insertUser(UserDto userDto,int id);
    //UserDto getUserById(int id);
}
