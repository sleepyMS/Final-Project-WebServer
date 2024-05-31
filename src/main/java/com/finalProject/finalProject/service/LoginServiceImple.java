package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.UserDaoImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImple implements LoginService{

    @Autowired
    private UserDaoImple userDaoImple;

    @Override
    public boolean isOk(String email, String password) {
        return userDaoImple.getAllUser().stream()
                .anyMatch(m -> m.getEmail().equals(email) && m.getPassword().equals(password));
    }


}
