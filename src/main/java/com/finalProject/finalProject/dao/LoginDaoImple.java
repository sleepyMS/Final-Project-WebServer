package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.LoginDto;
import com.finalProject.finalProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginDaoImple implements LoginDao{

    private LoginDto loginDto;

    @Override
    public String getEmail() {
        return loginDto.getEmail();
    }

    @Override
    public String getPassword() {
        return loginDto.getPassword();
    }
}
