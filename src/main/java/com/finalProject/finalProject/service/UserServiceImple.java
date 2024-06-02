package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.UserDaoImple;
import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserDaoImple userDaoImple;

    @Override
    public UserDto insertUser(SignUpDto signUpDto) {
        UserDto userDto = new UserDto();
        if (signUpDto != null) {
            // 모든 유저 이메일을 배열로 가져옴
            String[] allEmails = userDaoImple.getUserEmail();

            // 이메일 중복 확인
            if (Arrays.asList(allEmails).contains(signUpDto.getEmail())) {
                System.out.println("중복된 이메일입니다!");
                return null; // 중복된 이메일일 경우 null을 반환
            }

            userDto.setEmail(signUpDto.getEmail());
            userDto.setName(signUpDto.getName());
            userDto.setPassword(signUpDto.getPassword());
            userDto.setBirth(signUpDto.getBirth());
            userDto.setMbti(signUpDto.getMbti());
            userDto.setNick(signUpDto.getNick());
            userDto.setPhone(signUpDto.getPhone());
            userDto.setUserOTP("");

            if (areYouAdmin(signUpDto.getEmail())) {
                userDto.setId(0);
                userDto.setAdmin(true);
            } else {
                userDto.setId(userDaoImple.count() + 1);
                userDto.setAdmin(false);
            }

            userDaoImple.insertUser(userDto);
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAllUser() {
        return userDaoImple.getAllUser();
    }

    @Override
    public boolean isOk(String email, String password) {
        return userDaoImple.getAllUser().stream()
                .anyMatch(m -> m.getEmail().equals(email) && m.getPassword().equals(password));
    }

    @Override
    public UserDto getUserById(int id) {
        return userDaoImple.getAllUser().stream().filter(m -> m.getId() == id).findAny().get();
    }

    //DaoImple에 있어야하나?
    public void setUserOTP(String email){
        UserDto userDto = userDaoImple.getUserByEmail(email);
        if(userDto != null){
            int otp = (int) (Math.random() * 1000); // 0에서 999 사이의 난수 생성
            userDto.setUserOTP(String.valueOf(otp));
        }
    }

    public boolean areYouAdmin(String email){
        boolean b = false;
        if(email.contains("admin")) {
            b = true;
        }
        return b;
    }

    public void changePassword(int id,String currentPassword, String newPassword, String checkNewPassword){
        UserDto userDto = userDaoImple.getUserById(id);
        if(currentPassword.equals(userDto.getPassword())){
            if(newPassword.equals(checkNewPassword)){
                userDto.setPassword(newPassword);
                userDto.setUserOTP("");
            }
            else
                System.out.println("check newPassword,checkNewPassword");
        }
        else
            System.out.println("check currentPassword");
    }
}
