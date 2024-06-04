package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dao.UserDaoImple;
import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserDaoImple userDaoImple;

    @Override
    public UserDto insertUser(SignUpDto signUpDto) {
        LocalDate localDate = LocalDate.now();
        UserDto userDto = new UserDto();
        if (signUpDto != null) {
            userDto.setEmail(signUpDto.getEmail());
            userDto.setName(signUpDto.getName());
            userDto.setPassword(signUpDto.getPassword());
            userDto.setBirth(signUpDto.getBirth());
            userDto.setMbti(signUpDto.getMbti());
            userDto.setNick(signUpDto.getNick());
            userDto.setPhone(signUpDto.getPhone());
            userDto.setUserOTP("");
            userDto.setCurrentDate(localDate);

            if (areYouAdmin(signUpDto.getEmail())) {
                userDto.setId(0);
                userDto.setAdmin(true);
            } else {
                userDto.setId(userDaoImple.getMaxId() + 1);
                userDto.setAdmin(false);
            }

            userDaoImple.insertUser(userDto);
        }
        return userDto;
    }

//    public int validationSignUp(SignUpDto signUpDto) {
//        if (signUpDto == null) {
//            return -1;
//        }
//
//        // 모든 유저 이메일을 배열로 가져옴
//        String[] allEmails = userDaoImple.getUserEmail();
//        String[] allNicks = userDaoImple.getUserNick();
//
//        // 닉네임 중복 확인
//        if (Arrays.asList(allNicks).contains(signUpDto.getNick())) {
//            System.out.println("This is a duplicate nick!");
//            return 0;
//        }
//
//        // 이메일 중복 확인
//        if (Arrays.asList(allEmails).contains(signUpDto.getEmail())) {
//            System.out.println("This is a duplicate email!");
//            return 1;
//        }
//
//        // 비밀번호 확인
//        if (!signUpDto.getPassword().equals(signUpDto.getCheckPassword())) {
//            System.out.println("Check password");
//            return 2;
//        }
//
//        return -1; // 유효성 검사 통과
//    }

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

    public UserDto deleteUser(UserDto userDto){
        userDaoImple.deleteUser(userDto);
        return userDto;
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

    public void changeMbti(int id,String mbti) {
        UserDto userDto = userDaoImple.getUserById(id);
        userDto.setMbti(mbti);
        userDto.setCurrentDate(LocalDate.now());
    }

}
