package com.big_chat_brasil.bigchatbrasil.dto;

import java.math.BigDecimal;

public class ClientBalanceDTO {
    private Long clientId;
    private BigDecimal availableBalance;
    private BigDecimal usedCredit;
    private BigDecimal creditLimit;

    public ClientBalanceDTO(Long clientId ,BigDecimal availableBalance , BigDecimal usedCredit, BigDecimal creditLimit ) {
        this.clientId = clientId;
        this.availableBalance = availableBalance;
        this.usedCredit = usedCredit;
        this.creditLimit = creditLimit;
    }

    public Long getClientId() {
        return clientId;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public BigDecimal getUsedCredit() {
        return usedCredit;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }
}
