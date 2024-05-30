package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.SignUpDaoImple;
import com.finalProject.finalProject.dto.LoginDto;
import com.finalProject.finalProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignUpServiceImple implements SignUpService {

    @Autowired
    private SignUpDaoImple signUpDaoImple;

    @Override
    public SignUpDto insertUser(SignUpDto signUpDto, int id) {
        signUpDto.setId(signUpDaoImple.count() + 1); // 새로운 사용자를 추가할 때마다 ID 증가
        signUpDaoImple.insertUser(signUpDto);
        return signUpDto;
    }

    @Override
    public List<SignUpDto> getAllUser() {
        return signUpDaoImple.getAllUser();
    }

    public boolean isOk(String email, String password) {
        return signUpDaoImple.getAllUser().stream()
                .anyMatch(m -> m.getEmail().equals(email) && m.getPassword().equals(password));
    }

    @Override
    public SignUpDto getUserById(int id) {
        return signUpDaoImple.getAllUser().stream().filter(m -> m.getId() == id).findAny().get();
    }
}
