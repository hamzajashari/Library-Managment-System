package com.mnb.controller;


import com.mnb.entity.LibraryCart;
import com.mnb.entity.User;
import com.mnb.service.LibraryCartService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/library-cart")
public class LibraryCartController {

    private final LibraryCartService libraryCartService;

    public LibraryCartController(LibraryCartService libraryCartService) {
        this.libraryCartService = libraryCartService;
    }

    @GetMapping
    public String getLibraryCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
//        String username = req.getRemoteUser();
        User user = (User) req.getSession().getAttribute("user");
        LibraryCart libraryCart = this.libraryCartService.getActiveLibraryCart(user.getUsername());
        model.addAttribute("books", this.libraryCartService.listAllBooksInLibraryCart(libraryCart.getId()));
        model.addAttribute("bodyContent", "library-cart");
        return "master-template";
    }
    @PostMapping("/add-book/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) req.getSession().getAttribute("user");
           // User user = (User) authentication.getPrincipal();
           // this.libraryCartService.addBookToLibraryCart(user.getUsername(), id);
            this.libraryCartService.addBookToLibraryCart(user.getUsername(), id);
            return "redirect:/library-cart";
        } catch (RuntimeException exception) {
            return "redirect:/library-cart?error=" + exception.getMessage();
        }
    }


}
