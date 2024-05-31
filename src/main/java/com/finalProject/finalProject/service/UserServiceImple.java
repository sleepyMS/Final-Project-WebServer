package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.UserDaoImple;
import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserDaoImple userDaoImple;

    @Override
    public UserDto insertUser(SignUpDto signUpDto) {
        UserDto userDto = new UserDto();
        if(signUpDto != null){
            userDto.setId(userDaoImple.count() + 1);
            userDto.setEmail(signUpDto.getEmail());
            userDto.setName(signUpDto.getName());
            userDto.setPassword(signUpDto.getPassword());
            userDto.setBirth(signUpDto.getBirth());
            userDto.setMbti(signUpDto.getMbti());
            userDto.setNick(signUpDto.getNick());
            userDto.setPhone(signUpDto.getPhone());
        }
        userDaoImple.insertUser(userDto);
        return userDto;
    }


    @Override
    public List<UserDto> getAllUser() {
        return userDaoImple.getAllUser();
    }

    public boolean isOk(String email, String password) {
        return userDaoImple.getAllUser().stream()
                .anyMatch(m -> m.getEmail().equals(email) && m.getPassword().equals(password));
    }

    @Override
    public UserDto getUserById(int id) {
        return userDaoImple.getAllUser().stream().filter(m -> m.getId() == id).findAny().get();
    }
}
