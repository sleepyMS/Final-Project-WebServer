package com.finalProject.finalProject.service;

import com.finalProject.finalProject.dto.LoginDto;
import com.finalProject.finalProject.dto.SignUpDto;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface LoginService {
    boolean isOk(String email, String password);
}
