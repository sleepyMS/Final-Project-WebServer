package com.finalProject.finalProject.dao;

import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoImple implements UserDao {

    private List<UserDto> database = new ArrayList<>();

    public UserDaoImple() {
         System.out.println("UserDaoImple 객체 생성");
        database.add(new UserDto(0, "최민석", "admin@123", "123", "2001-10-17", "ENFJ", "밍식", "3924",true,"",LocalDate.of(2024, 1, 24)));
        database.add(new UserDto(1, "김정호", "123@123", "123", "2000-08-16", "ISFJ", "정오", "123",false,"", LocalDate.of(2024, 2, 24)));
        database.add(new UserDto(2, "박준형", "234@123", "123", "123", "ISTJ", "주녕", "123",false,"", LocalDate.of(2024, 3, 25)));
        database.add(new UserDto(3, "김민재", "345@123", "123", "123", "ESTJ", "민재", "123",false,"", LocalDate.of(2024, 4, 25)));
        database.add(new UserDto(4, "손흥민", "456@123", "123", "123", "ESFJ", "흥민", "123",false,"", LocalDate.of(2024, 5, 25)));
        database.add(new UserDto(5, "황희찬", "567@123", "123", "123", "ISFJ", "희찬", "123",false,"", LocalDate.of(2024, 6, 5)));
        database.add(new UserDto(6, "김철수", "678@123", "123", "123", "ISTP", "철수", "123",false,"", LocalDate.of(2024, 1, 25)));
        database.add(new UserDto(7, "신짱구", "789@123", "123", "123", "ESTP", "짱구", "123",false,"", LocalDate.of(2024, 2, 25)));
        database.add(new UserDto(8, "고맹구", "8910@123", "123", "123", "INTJ", "맹구", "123",false,"", LocalDate.of(2024, 3, 25)));
        database.add(new UserDto(9, "이강인", "91011@123", "123", "123", "ENFJ", "강인", "123",false,"", LocalDate.of(2024, 4, 25)));
        database.add(new UserDto(10, "송정우", "91012@123", "123", "123", "ISFJ", "정우", "123",false,"", LocalDate.of(2024, 5, 25)));
        database.add(new UserDto(11, "황인범", "91013@123", "123", "123", "INTJ", "인범", "123",false,"", LocalDate.of(2024, 6, 8)));
    }

    @Override
    public int count() {
        return database.size();
    }

    @Override
    public String[] getUserEmail() {
        List<String> emails = new ArrayList<>();
        for (UserDto user : database) {
            emails.add(user.getEmail());
        }
        return emails.toArray(new String[0]);
    }
    @Override
    public String[] getUserNick() {
        List<String> nicks = new ArrayList<>();
        for (UserDto user : database) {
            nicks.add(user.getNick());
        }
        return nicks.toArray(new String[0]);
    }
    @Override
    public int getMaxId() {
        return database.stream()
                .mapToInt(UserDto::getId)
                .max()
                .orElse(0); // 데이터베이스가 비어있을 경우 0 반환
    }

    @Override
    public UserDto getUserById(int id) {
        for (UserDto user : database) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // 해당 ID를 가진 사용자가 없을 경우
    }
    @Override
    public UserDto getUserByEmail(String email) {
        for (UserDto user : database) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null; // 해당 ID를 가진 사용자가 없을 경우
    }

    @Override
    public List<UserDto> getAllUser() {
        System.out.println(database);
        return database;
    }

    @Override
    public UserDto insertUser(UserDto userDto) {
        database.add(userDto);
        return userDto;
    }
    @Override
    public UserDto deleteUser(UserDto userDto){
        database.remove(userDto);
        return userDto;
    }
}
