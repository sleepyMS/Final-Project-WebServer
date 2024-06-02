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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/checkEmail")
    public String checkEmail(Model model) {
        return "checkEmail";
    }

    @RequestMapping("/changePassword/{id}")
    public String changePassword(@PathVariable("id") int id, Model model) {
        model.addAttribute("id",id);
        return "changePassword" ;
    }

    @RequestMapping(value ="/checkOTP")
    public String checkOTP(@RequestParam("id") int id, @RequestParam("OTP") String OTP, RedirectAttributes redirectAttributes) {
        if (userServiceImple.getUserById(id).getUserOTP().equals(OTP)) {
            return "redirect:/user/auth/changePassword/" + id;
        } else {
            return "redirect:/user/auth/inputOTP/" + id + "?error=true";
        }
    }

    @RequestMapping("/inputOTP/{id}")
    public String inputOTP(@PathVariable int id,Model model) {
        model.addAttribute("user",userServiceImple.getUserById(id));
        return "inputOTP";
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

    @RequestMapping("/getCurrentUserId")
    @ResponseBody
    public int getCurrentUserId(HttpSession session) {
        UserDto currentUserDto = (UserDto) session.getAttribute("currentUserDto");
        if (currentUserDto != null) {
            return currentUserDto.getId();
        } else {
            return -1;
        }
    }

    @RequestMapping("/myPage/{id}")
    public String myPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userServiceImple.getUserById(id));
        return "myPage";
    }

    @RequestMapping("/authNum")
    public String authNum(@RequestParam("email") String email) {
        userServiceImple.setUserOTP(email);
        return "redirect:/";
    }

    @RequestMapping("/resultPassword")
    public String resultPassword(@RequestParam("currentPassword") String currentPassword
                                 ,@RequestParam("newPassword") String newPassword
                                 ,@RequestParam("checkNewPassword") String checkNewPassword
                                 ,@RequestParam("id") int id
                                 ) {
        userServiceImple.changePassword(id,currentPassword,newPassword,checkNewPassword);
        return "redirect:/user/auth/resultPassword";
    }
}

