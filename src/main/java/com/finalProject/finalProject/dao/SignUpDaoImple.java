package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.SignUpDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignUpDaoImple implements SignUpDao {

    private List<SignUpDto> database = new ArrayList<>();

    public SignUpDaoImple() {
        System.out.println("UserDaoImple 객체 생성");
        database.add(new SignUpDto(1, "김", "EWQE", "123", "123", "123", "123", "123", "123"));
    }

    @Override
    public int count() {
        return database.size();
    }

    @Override
    public SignUpDto getUserById(int id) {
        for (SignUpDto user : database) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // 해당 ID를 가진 사용자가 없을 경우
    }

    @Override
    public List<SignUpDto> getAllUser() {
        return database;
    }

    @Override
    public SignUpDto insertUser(SignUpDto signUpDto) {
        database.add(signUpDto);
        return signUpDto;
    }
}
