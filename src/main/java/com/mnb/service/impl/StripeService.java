package com.mnb.service.impl;

import com.mnb.dtos.StripeChargeRequestDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    private static final String STRIPE_SECRET_KEY = "sk_test_51KuGsBGsmQFgruPXULcodTJYYtDWBmgCqzn9lpZyj6xPNHxjmm0YahxqiLsFovwmuyWJAIk6F1tjwzewlSG2Avua00CGlDpyka";

    @PostConstruct
    public void init() {
        Stripe.apiKey = STRIPE_SECRET_KEY;
    }
    public Charge charge(StripeChargeRequestDTO chargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }

}