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

import java.time.LocalDate;

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

    @RequestMapping("/userList")
    public String userList(Model model) {
        model.addAttribute("users",userServiceImple.getAllUser());
        return "userList";
    }

    @RequestMapping("/changeMbti/{id}")
    public String changeMbti(@PathVariable("id") int id, Model model) {
        model.addAttribute("id",id);
        return "changeMbti";
    }

    @RequestMapping("/resultMbti")
    public String resultMbti(@RequestParam("id") int id,
                             @RequestParam("mbti") String mbti,
                             Model model) {
        userServiceImple.changeMbti(id,mbti);
        return "redirect:/user/auth/myPage/" + id ;
    }

    @RequestMapping("/authNum")
    public String authNum(@RequestParam("email") String email) {
        userServiceImple.setUserOTP(email);
        return "redirect:/";
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
        int validationCode = userServiceImple.validationSignUp(signUpDto);

        if (validationCode == -1) {
            if (userServiceImple.insertUser(signUpDto) != null) {
                System.out.println(userServiceImple.getAllUser());
                return "redirect:/";
            }
        } else {
            model.addAttribute("i", validationCode);
            return "signUp"; // 기존 signUp.html 페이지로 이동
        }
        return "signUp"; // 예기치 않은 경우
//        if (userServiceImple.insertUser(signUpDto) != null) {
//                System.out.println(userServiceImple.getAllUser());
//                return "redirect:/";
//        }
//        else
//           return "redirect:/user/auth/signIn?error=true";
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
        UserDto userDto =  userServiceImple.getUserById(id);
        LocalDate localDate = LocalDate.now();
        int userDate = userDto.getCurrentDate().getDayOfYear();
        int currentDate = localDate.getDayOfYear();
        model.addAttribute("user", userDto);
        model.addAttribute("userDate", userDate);
        model.addAttribute("currentDate", currentDate);
        return "myPage";
    }


    @RequestMapping("/changePassword/{id}")
    public String changePassword(@PathVariable("id") int id, Model model) {
        model.addAttribute("id",id);
        return "changePassword" ;
    }

    @RequestMapping("/resultPassword")
    public String resultPassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("checkNewPassword") String checkNewPassword,
                                 @RequestParam("id") int id,
                                 RedirectAttributes redirectAttributes) {
        int check = userServiceImple.changePassword(id, currentPassword, newPassword, checkNewPassword);
        System.out.println(check);
        if (check == 0) {
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("passwordChangeStatus", check);
            return "redirect:/user/auth/changePassword/" + id;
        }
    }




    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userServiceImple.deleteUser(userServiceImple.getUserById(id));
        return "redirect:/user/auth/userList";
    }
}

