package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.UserDaoImple;
import com.finalProject.finalProject.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImple implements LoginService{

    @Autowired
    private UserDaoImple userDaoImple;
//
    @Override
    public boolean isOk(String email, String password) {
        return userDaoImple.getAllUser().stream()
                .anyMatch(m -> m.getEmail().equals(email) && m.getPassword().equals(password));
    }

    public LoginDto setLogin(String email,String password){
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(email);
        loginDto.setPassword(password);

        return loginDto;
    }

}
