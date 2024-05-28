package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.SignUpDaoImple;
import com.finalProject.finalProject.dto.LoginDto;
import com.finalProject.finalProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class LoginServiceImple implements LoginService{

    @Autowired
    private SignUpDaoImple signUpDaoImple;

    @Override
    public boolean isOk(String email, String password) {
        return signUpDaoImple.getAllUser().stream()
                .anyMatch(m -> m.getEmail().equals(email) && m.getPassword().equals(password));
    }


}
