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

    @GetMapping("/list")
    public String listBooks(Model theModel) {
        // get employees from db
        List<Book> theBooks = bookService.findAll();
        // add to the spring model
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Book theBook = new Book();
        theModel.addAttribute("books", theBook);
        theModel.addAttribute("authors",authorService.findAll());
        theModel.addAttribute("publishers",publisherService.findAll());
        return "book-form";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theID, Model theModel) {
        //get the book from the service
        Book theBook = bookService.findById(theID);
        //set book as a model attribute to pre-populate the form
        theModel.addAttribute("books", theBook);
        return "book-form";
    }
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("books") Book theBook) {
        // save the book
        bookService.save(theBook);
        // use a redirect to prevent duplicate submissions
        return "redirect:/books/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {
        // delete the book
        bookService.deleteById(theId);
        return "redirect:/books/list";
    }

    @GetMapping("/search")
    public String findBookByName(Model model, @Param("keyword") String keyword){
        model.addAttribute("books", bookService.findBookByBookName(keyword));
        return "list-books";
    }
}
