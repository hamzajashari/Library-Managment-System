package com.mnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-profile")
public class UserprofileController {

    @GetMapping()
    public String userprofile(Model model) {
        model.addAttribute("bodyContent","user-profile");
        return "master-template";
    }
}
