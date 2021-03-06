package com.mnb.dtos;

import lombok.Data;

@Data
public class StripeChargeRequestDTO {


        public enum Currency {
            EUR, USD;
        }
        private String description;
        private int amount;
        private Currency currency;
        private String stripeEmail;
        private String stripeToken;

}
