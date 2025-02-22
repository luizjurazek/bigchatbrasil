package com.big_chat_brasil.bigchatbrasil.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class AlterClientLimitDTO {
    @Schema(description = "New credit limit to be added to the client",
            example = "100.50",
            required = true)
  private BigDecimal newLimit;

    // Getters e Setters
    public BigDecimal getNewLimit() {
        return newLimit;
    }

    public void setNewLimit(BigDecimal newLimit) {
        this.newLimit = newLimit;
    }
}