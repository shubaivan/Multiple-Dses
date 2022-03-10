package com.example.card_server.dto;

import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MainRequestDTO {

    //5577000055770004(5577 0000 5577 0004) mastercard example
    //4111111111111111 visa
    @NotBlank
    @NotNull
    @CreditCardNumber(ignoreNonDigitCharacters = true)
    private String acctNumber;

    public MainRequestDTO() {
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber.replaceAll("\\s+", "");
    }
}
