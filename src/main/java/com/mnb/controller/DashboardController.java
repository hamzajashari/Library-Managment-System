package com.mnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping()
    public String contactus(Model model) {
        model.addAttribute("bodyContent","dashboard");
        return "master-template";
    }
}
