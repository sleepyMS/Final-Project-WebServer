package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.UserDao;
import com.finalProject.finalProject.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImple implements UserDao {

    private List<UserDto> database = new ArrayList<>();
    private int currentId = 0; // 현재 사용자 ID 추적

    public UserDaoImple() {
        System.out.println("UserDaoImple 객체 생성");
        database.add(new UserDto(++currentId, "김", "EWQE", "123", "123", "123", "123", "123", "123"));
    }

    @Override
    public int count() {
        return database.size();
    }

    @Override
    public UserDto getUserById(int id) {
        for (UserDto user : database) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // 해당 ID를 가진 사용자가 없을 경우
    }

    @Override
    public List<UserDto> getUser() {
        return database;
    }

    @Override
    public UserDto insertUser(UserDto userDto, int id) {
        userDto.setId(++currentId); // 새로운 사용자를 추가할 때마다 ID 증가
        database.add(userDto);
        return userDto;
    }
}
