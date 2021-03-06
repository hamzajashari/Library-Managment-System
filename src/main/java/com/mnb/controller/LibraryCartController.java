package com.mnb.controller;


import com.mnb.dtos.StripeChargeRequestDTO;
import com.mnb.entity.Book;
import com.mnb.entity.LibraryCart;
import com.mnb.entity.User;
import com.mnb.service.LibraryCartService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import java.security.Principal;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/library-cart")
public class LibraryCartController {

    private static final String STRIPE_PUBLISHABLE_KEY = "pk_test_51KuGsBGsmQFgruPXC2WDKq7q90Tpn3Nv1dqgAwPJhfkIjbS5x8fjxZUP79jCon3NElA1653vmCW2Np6MNQw7Ib5v008R886Pso";
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
        String username = req.getRemoteUser();
        LibraryCart libraryCart = this.libraryCartService.getActiveLibraryCart(username);
        List<Book> bookList = this.libraryCartService.listAllBooksInLibraryCart(libraryCart.getId());

        model.addAttribute("bodyContent", "library-cart");
        model.addAttribute("librarycart", libraryCart);
        model.addAttribute("books", bookList);
        model.addAttribute("datecreated",libraryCart.getDateCreated().toLocalDate());

        //added for stripe
        model.addAttribute("amount", this.libraryCartService.charge(libraryCart.getId())*100);
        model.addAttribute("stripePublicKey", STRIPE_PUBLISHABLE_KEY);
        model.addAttribute("currency", StripeChargeRequestDTO.Currency.EUR);
        return "master-template";
    }
    @PostMapping("/add-book/{id}")
    public String addBookToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        try {
            String username = req.getRemoteUser();
            this.libraryCartService.addBookToLibraryCart(username, id);
            return "redirect:/library-cart";
        } catch (RuntimeException exception) {
            return "redirect:/library-cart?error=" + exception.getMessage();
        }
    }

    @PostMapping("/payment/{id}")
    public String Payment(@PathVariable Long id) {
        try {
            this.libraryCartService.pay(id);
            return "redirect:/library-cart";
        } catch (RuntimeException exception) {
            return "redirect:/library-cart?error=" + exception.getMessage();
        }
    }

}
