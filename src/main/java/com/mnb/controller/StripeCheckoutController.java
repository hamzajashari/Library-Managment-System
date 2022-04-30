package com.mnb.controller;


import com.mnb.dtos.StripeChargeRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class StripeCheckoutController {

    private static final String STRIPE_PUBLISHABLE_KEY = "pk_test_51KuGsBGsmQFgruPXC2WDKq7q90Tpn3Nv1dqgAwPJhfkIjbS5x8fjxZUP79jCon3NElA1653vmCW2Np6MNQw7Ib5v008R886Pso";


    @GetMapping
    public String checkout(Model model) {
        model.addAttribute("bodyContent","checkout");
        model.addAttribute("amount", 50*100); // amount in cents
        model.addAttribute("stripePublicKey", STRIPE_PUBLISHABLE_KEY);
        model.addAttribute("currency", StripeChargeRequestDTO.Currency.EUR);
        return "master-template";
    }

}
