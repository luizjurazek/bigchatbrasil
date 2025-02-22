package com.big_chat_brasil.bigchatbrasil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public class ClientBalanceDTO {
    
    @Schema(description = "The unique ID of the client", example = "1")
    private Long clientId;

    @Schema(description = "The available credit balance for the client", example = "1000.00")
    private BigDecimal availableBalance;

    @Schema(description = "The amount of credit already used by the client", example = "200.00")
    private BigDecimal usedCredit;

    @Schema(description = "The total credit limit of the client", example = "1500.00")
    private BigDecimal creditLimit;

    // Constructor
    public ClientBalanceDTO(Long clientId, BigDecimal availableBalance, BigDecimal usedCredit, BigDecimal creditLimit) {
        this.clientId = clientId;
        this.availableBalance = availableBalance;
        this.usedCredit = usedCredit;
        this.creditLimit = creditLimit;
    }

    // Getters
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

