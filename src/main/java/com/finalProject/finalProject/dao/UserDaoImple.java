package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImple implements UserDao {

    private List<UserDto> database = new ArrayList<>();

    public UserDaoImple() {
        System.out.println("UserDaoImple 객체 생성");
        database.add(new UserDto(1, "김", "123", "123", "123", "123", "123", "123"));
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
    public UserDto getUserByEmail(String email) {
        for (UserDto user : database) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null; // 해당 ID를 가진 사용자가 없을 경우
    }

    @Override
    public List<UserDto> getAllUser() {
        System.out.println(database);
        return database;
    }

    @Override
    public UserDto insertUser(UserDto userDto) {
        database.add(userDto);
        return userDto;
    }

}
