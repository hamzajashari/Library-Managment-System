package com.mnb.controller;

import com.mnb.entity.User;
import com.mnb.entity.exception.InvalidUserCredentialsException;
import com.mnb.service.AuthService;
import com.mnb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String showMyLoginPage(Model model) {
        model.addAttribute("bodyContent","login");
        return "master-template";
    }
    @PostMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
