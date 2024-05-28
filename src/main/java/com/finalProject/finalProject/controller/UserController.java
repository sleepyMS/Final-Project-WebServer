package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/auth")
public class UserController {

    @PostMapping("/signUp")
    public String signUp(@RequestBody UserDto requestBod, Model model) {

        model.addAttribute(requestBod);
//        아무 말
        return "1";
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody UserDto requestBod, Model model) {

        model.addAttribute(requestBod);
        return "1";
    }

}
