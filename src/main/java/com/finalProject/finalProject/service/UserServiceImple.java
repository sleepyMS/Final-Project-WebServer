package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.UserDaoImple;
import com.finalProject.finalProject.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImple implements UserService{

    @Autowired
    private UserDaoImple userDaoImple;

    private int currentId = 1; // 현재 사용자 ID 추적

    @Override
    public UserDto insertUser(UserDto userDto, int id) {
        userDto.setId(++currentId); // 새로운 사용자를 추가할 때마다 ID 증가
        userDaoImple.insertUser(userDto);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUser() {
        return userDaoImple.getAllUser();
    }
}
