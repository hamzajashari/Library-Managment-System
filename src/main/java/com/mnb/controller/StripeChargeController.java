package com.mnb.controller;


import com.mnb.dtos.StripeChargeRequestDTO;
import com.mnb.service.impl.StripeService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/charge")
public class StripeChargeController {

    private final StripeService paymentsService;

    public StripeChargeController(StripeService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public String get(Model model){
        model.addAttribute("bodyContent","result");
        return "master-template";
    }
    @PostMapping
    public String charge(StripeChargeRequestDTO chargeRequest, Model model) throws StripeException {
        model.addAttribute("bodyContent","result");
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(StripeChargeRequestDTO.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "master-template";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("bodyContent","result");
        model.addAttribute("error", ex.getMessage());
        return "master-template";
    }
}
