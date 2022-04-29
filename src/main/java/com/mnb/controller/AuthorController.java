package com.mnb.controller;

import com.mnb.entity.Author;
import com.mnb.service.AuthorService;
import com.mnb.service.BookService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
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
        List<Author> authors = authorService.findAll();
        // add to the spring model
        model.addAttribute("authors", authors);
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


    @GetMapping("/edit/{id}")
    public String showFormForUpdate(@PathVariable Long id, Model model) {
        //get the author from the service
        Author author = authorService.findById(id);
        //set author as a model attribute to pre-populate the form
        model.addAttribute("authors", author);
        model.addAttribute("bodyContent","author-form");

        return "master-template";
    }
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("authors") Author Author) {
        // save the author
        authorService.save(Author);
        // use a redirect to prevent duplicate submissions
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        // delete the author
        authorService.deleteById(id);
        // redirect to /author/list
        return "redirect:/authors";
    }
    @GetMapping("/search")
    public String findBookByName(Model model, @Param("keyword") String keyword){
        model.addAttribute("bodyContent","list-authors");
        model.addAttribute("authors", authorService.findByAuthorName(keyword));
        return "master-template";
    }
}
