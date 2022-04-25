package com.mnb.controller;

import com.mnb.entity.Publisher;
import com.mnb.service.PublisherService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/publishers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherController {
    final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @GetMapping()
    public String listPublishers(Model model) {

        // get publisher from db
        List<Publisher> thePublishers = publisherService.findAll();
        model.addAttribute("bodyContent","list-publishers");
        // add to the spring model
        model.addAttribute("publishers", thePublishers);

        return "master-template";
    }
    @GetMapping("/add")
    public String showFormForAdd(Model model) {

        // create model attribute to bind form data
        Publisher thePublisher = new Publisher();
        model.addAttribute("bodyContent","publisher-form");
        model.addAttribute("publishers", thePublisher);

        return "master-template";
    }


    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable Long id, Model model) {
        //get the publisher from the service
        Publisher thePublisher = publisherService.findById(id);
        //set publisher as a model attribute to pre-populate the form
        model.addAttribute("publishers", thePublisher);
        model.addAttribute("bodyContent","publisher-form");
        return "master-template";
    }
    @PostMapping("/save")
    public String savePublisher(@ModelAttribute("publishers") Publisher thePublisher) {
        // save the publisher
        publisherService.save(thePublisher);
        // use a redirect to prevent duplicate submissions
        return "redirect:/publishers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        publisherService.deleteById(id);
        return "redirect:/publishers";

    }
}
