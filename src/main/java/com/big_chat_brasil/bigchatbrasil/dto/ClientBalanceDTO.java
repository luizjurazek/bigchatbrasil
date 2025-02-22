package com.big_chat_brasil.bigchatbrasil.dto;

import java.math.BigDecimal;

public class ClientBalanceDTO {
  private Long clientId;
    private BigDecimal availableBalance;

    public ClientBalanceDTO(Long clientId, BigDecimal availableBalance) {
        this.clientId = clientId;
        this.availableBalance = availableBalance;
    }

    public Long getClientId() {
        return clientId;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }
}
