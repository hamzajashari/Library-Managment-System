package com.mnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about-us")
public class AboutusController {

    @GetMapping()
    public String aboutus(Model model) {
        model.addAttribute("bodyContent","aboutus");
        return "master-template";
    }
}
