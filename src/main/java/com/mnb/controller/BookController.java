package com.mnb.controller;

import com.mnb.entity.Book;
import com.mnb.service.AuthorService;
import com.mnb.service.BookService;
import com.mnb.service.PublisherService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookController {
    final BookService bookService;
    final AuthorService authorService;
    final PublisherService publisherService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @GetMapping()
    public String listBooks(Model model) {
        // get employees from db
        List<Book> theBooks = bookService.findAll();
        // add to the spring model
        model.addAttribute("bodyContent","list-books");
        model.addAttribute("books", theBooks);
        return "master-template";
    }
    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        // create model attribute to bind form data
        Book theBook = new Book();
        model.addAttribute("bodyContent","book-form");
        model.addAttribute("books", theBook);
        model.addAttribute("authors",authorService.findAll());
        model.addAttribute("publishers",publisherService.findAll());
        return "master-template";
    }


    @GetMapping("/edit/{bookId}")
    public String showFormForUpdate(@RequestParam("bookId") int theID, Model model) {
        //get the book from the service
        Book theBook = bookService.findById(theID);
        model.addAttribute("bodyContent","book-form");
        //set book as a model attribute to pre-populate the form
        model.addAttribute("books", theBook);

        return "master-template";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("books") Book theBook) {
        // save the book
        bookService.save(theBook);
        // use a redirect to prevent duplicate submissions
        return "redirect:/books";
    }

    @GetMapping("/delete/{bookId}")
    public String delete(@RequestParam("bookId") int theId) {
        // delete the book
        bookService.deleteById(theId);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String findBookByName(Model model, @Param("keyword") String keyword){
        model.addAttribute("bodyContent","list-books");
        model.addAttribute("books", bookService.findBookByBookName(keyword));
        return "master-template";
    }
}
