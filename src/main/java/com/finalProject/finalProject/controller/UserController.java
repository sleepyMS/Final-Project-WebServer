package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dao.UserDao;
import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.UserServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/auth")
public class UserController {

    @Autowired
    private UserServiceImple userServiceImple;

    @RequestMapping("/")
    public String home(){

        return "home";
    }

    @RequestMapping("/signUp")
    public String signUp(@ModelAttribute UserDto userDto,Model model) {
       return "signUp";
    }

    @RequestMapping("/checkSignUp")
    public String checkSignUp(@ModelAttribute UserDto userDto,Model model) {
        userServiceImple.insertUser(userDto, -1);
        model.addAttribute("users", userServiceImple.getAllUser());
        System.out.println(userServiceImple.getAllUser());
        return "checkSignUp";
    }

    @PostMapping("/signIn")
    public String signIn(UserDto requestBod, Model model) {
        model.addAttribute("user", requestBod);
        return "signIn";
    }
}

