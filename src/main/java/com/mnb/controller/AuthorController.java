package com.mnb.controller;

import com.mnb.entity.Author;
import com.mnb.service.AuthorService;
import com.mnb.service.BookService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorController {

    public static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    final BookService bookService;
    final AuthorService authorService;

    public AuthorController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @GetMapping()
    public String listAuthors(Model model) {
        // get author from db
        List<Author> theAuthors = authorService.findAll();
        // add to the spring model
        model.addAttribute("authors", theAuthors);
        model.addAttribute("bodyContent","list-authors");
        return "master-template";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        // create model attribute to bind form data
        Author theAuthor = new Author();
        model.addAttribute("authors", theAuthor);
        model.addAttribute("bodyContent","author-form");
        return "master-template";
    }


    @GetMapping("/edit/{authorId}")
    public String showFormForUpdate(@RequestParam("authorId") int theID, Model model) {
        //get the author from the service
        Author theAuthor = authorService.findById(theID);
        //set author as a model attribute to pre-populate the form
        model.addAttribute("authors", theAuthor);
        model.addAttribute("bodyContent","author-form");

        return "master-template";
    }
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("authors") Author theAuthor) {
        // save the author
        authorService.save(theAuthor);
        // use a redirect to prevent duplicate submissions
        return "redirect:/authors";
    }

    @GetMapping("/delete/{authorId}")
    public String delete(@RequestParam("authorId") int theId) {
        // delete the author
        authorService.deleteById(theId);
        // redirect to /author/list
        return "redirect:/authors";
    }
}
