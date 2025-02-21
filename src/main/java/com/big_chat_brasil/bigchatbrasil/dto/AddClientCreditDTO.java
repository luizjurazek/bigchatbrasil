package com.big_chat_brasil.bigchatbrasil.dto;

import java.math.BigDecimal;

public class AddClientCreditDTO {
  private BigDecimal newBalanceValue;

    // Getters e Setters
    public BigDecimal getNewBalanceCredit() {
        return newBalanceValue;
    }

    public void setNewBalanceCredit(BigDecimal newBalanceValue) {
        this.newBalanceValue = newBalanceValue;
    }
}