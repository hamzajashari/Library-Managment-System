package com.mnb.controller;


import com.mnb.dtos.StripeChargeRequestDTO;
import com.mnb.service.LibraryCartService;
import com.mnb.service.impl.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/charge")
public class StripeChargeController {

    private final StripeService paymentsService;
    private final LibraryCartService libraryCartService;

    public StripeChargeController(StripeService paymentsService,LibraryCartService libraryCartService) {
        this.paymentsService = paymentsService;
        this.libraryCartService=libraryCartService;
    }

    @GetMapping
    public String get(Model model){
        model.addAttribute("bodyContent","result");
        return "master-template";
    }
    @PostMapping("/{id}")
    public String charge(@PathVariable Long id, StripeChargeRequestDTO chargeRequest, Model model) throws StripeException {
        model.addAttribute("bodyContent","result");
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(StripeChargeRequestDTO.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        this.libraryCartService.pay(id);
        return "master-template";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("bodyContent","result");
        model.addAttribute("error", ex.getMessage());
        return "master-template";
    }
}
