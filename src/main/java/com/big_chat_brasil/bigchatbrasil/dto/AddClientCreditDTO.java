package com.big_chat_brasil.bigchatbrasil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public class AddClientCreditDTO {
    @Schema(description = "New credit balance to be added to the client",
            example = "100.50",
            required = true)
    private BigDecimal newBalanceValue;

    // Getters e Setters
    public BigDecimal getNewBalanceCredit() {
        return newBalanceValue;
    }

    public void setNewBalanceCredit(BigDecimal newBalanceValue) {
        this.newBalanceValue = newBalanceValue;
    }
}

