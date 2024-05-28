package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto insertUser(UserDto userDto, int id);
    List<UserDto> getAllUser();
}
