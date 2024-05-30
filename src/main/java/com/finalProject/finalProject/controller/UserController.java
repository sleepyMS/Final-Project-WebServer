package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dao.UserDaoImple;
import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.SignUpServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/auth")
public class UserController {

    @Autowired
    private SignUpServiceImple userServiceImple;

    @Autowired
    private UserDaoImple userDaoImple;

//    @RequestMapping("/")
//    public String home(){
//        return "home";
//    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @RequestMapping("/checkSignUp")
    public String checkSignUp(@ModelAttribute SignUpDto signUpDto, Model model) {
        userServiceImple.insertUser(signUpDto, -1);
        model.addAttribute("users", userServiceImple.getAllUser());
        System.out.println(userServiceImple.getAllUser());
        return "redirect:/";
    }

    @RequestMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    @RequestMapping(value = "/checkSignIn", method = RequestMethod.POST)
    public String checkSignIn(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        UserDto user = userDaoImple.setUser(email, password);

        if (user != null) {
            System.out.println("Login Success!");
            model.addAttribute("user", user);
            System.out.println(user);
            return "redirect:/user/auth/";
        } else {
            return "redirect:/user/auth/signIn?error=true";
        }
    }
}
