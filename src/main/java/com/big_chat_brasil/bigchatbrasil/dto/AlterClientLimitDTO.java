package com.big_chat_brasil.bigchatbrasil.dto;

import java.math.BigDecimal;

public class AlterClientLimitDTO {
  private BigDecimal newLimit;

    // Getters e Setters
    public BigDecimal getNewLimit() {
        return newLimit;
    }

    public void setNewLimit(BigDecimal newLimit) {
        this.newLimit = newLimit;
    }
}