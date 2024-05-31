package com.finalProject.finalProject.controller;

import com.finalProject.finalProject.dao.UserDaoImple;
import com.finalProject.finalProject.dto.SignUpDto;
import com.finalProject.finalProject.dto.UserDto;
import com.finalProject.finalProject.service.LoginServiceImple;
import com.finalProject.finalProject.service.UserServiceImple;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/auth")
public class UserController {

    @Autowired
    private UserServiceImple userServiceImple;

    @Autowired
    private UserDaoImple userDaoImple;

    @Autowired
    private LoginServiceImple loginServiceImple;


    @RequestMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @RequestMapping(value = "/checkSignIn", method = RequestMethod.POST)
    public String checkSignIn(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              HttpSession session,
                              Model model) {
        if (loginServiceImple.isOk(email, password)) {
            System.out.println("Login Success!");
            session.setAttribute("currentUserDto", userDaoImple.getUserByEmail(email));
            return "redirect:/";
        } else {
            return "redirect:/user/auth/signIn?error=true";
        }
    }

    @RequestMapping("/checkSignUp")
    public String checkSignUp(@ModelAttribute SignUpDto signUpDto, Model model) {
        userServiceImple.insertUser(signUpDto);
        System.out.println(userServiceImple.getAllUser());
        return "redirect:/";
    }

    @RequestMapping("/signOut")
    public String signOut(HttpSession session){
        session.invalidate(); // 세션 무효화
        //
        //
        return "redirect:/";
    }

    @RequestMapping("/getCurrentUserEmail")
    @ResponseBody
    public String getCurrentUserEmail(HttpSession session) {
        UserDto currentUserDto = (UserDto) session.getAttribute("currentUserDto");
        if (currentUserDto != null) {
            return currentUserDto.getNick();
        } else {
            return "No user logged in";
        }
    }

}

