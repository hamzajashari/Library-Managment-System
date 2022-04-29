package com.mnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact-us")
public class ContactUsController {

    @GetMapping()
    public String contactus(Model model) {
        model.addAttribute("bodyContent","contact-us");
        return "master-template";
    }
}
