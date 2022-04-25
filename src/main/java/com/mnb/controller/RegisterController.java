package com.mnb.controller;

import com.mnb.entity.Role;
import com.mnb.entity.exception.InvalidArgumentsException;
import com.mnb.entity.exception.PasswordsDoNotMatchException;
import com.mnb.service.AuthService;
import com.mnb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("bodyContent","register");
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "master-template";
    }

    @PostMapping
    public String register(
                           @RequestParam String name,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String dateOfBirth) {
        try {
            this.userService.register(username, password, repeatedPassword, name,dateOfBirth);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
