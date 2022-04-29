package com.mnb.controller;

import com.mnb.entity.User;
import com.mnb.entity.exception.InvalidArgumentsException;
import com.mnb.entity.exception.PasswordsDoNotMatchException;
import com.mnb.service.AuthService;
import com.mnb.service.UserService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user-profile")
public class UserprofileController {
    private final AuthService authService;
    private final UserService userService;

    public UserprofileController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping()
    public String userprofile(Model model) {
        model.addAttribute("bodyContent","user-profile");
        return "master-template";
    }
    @PostMapping()
    public String update(HttpServletRequest request,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "updatePassword") String UpdatePassword,
            @RequestParam(value = "dateOfBirth") String dateOfBirth, Model model) {
        try {
            String oldusername = request.getRemoteUser();
            this.authService.update(name,oldusername,username,password,UpdatePassword,dateOfBirth);
            model.addAttribute("username",username);
            return "redirect:/user-profile";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/user-profile?error=" + exception.getMessage();
        }
    }
    @PostMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
