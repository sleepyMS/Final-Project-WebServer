package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImple {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SignUpDaoImple signUpDaoImple;

    public UserDto setUser(String email, String password){
        if(loginService.isOk(email, password)) {
            SignUpDto signUpDto = signUpDaoImple.getAllUser().stream()
                    .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                    .findAny()
                    .orElse(null);

            if (signUpDto != null) {
                return new UserDto(
                        signUpDto.getId(),
                        signUpDto.getName(),
                        signUpDto.getEmail(),
                        signUpDto.getPassword(),
                        signUpDto.getBirth(),
                        signUpDto.getMbti(),
                        signUpDto.getNick(),
                        signUpDto.getPhone()
                );
            }
        }
        return null;
    }
}
