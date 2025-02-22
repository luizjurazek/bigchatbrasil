package com.big_chat_brasil.bigchatbrasil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class SMS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier for the SMS record", example = "1")
    private Long id;

    @Schema(description = "The phone number to which the SMS is sent", example = "(11) 91234-5678")
    private String phoneNumber;

    @Schema(description = "Indicates whether the message is sent via WhatsApp", example = "true")
    private boolean isWhatsApp;

    @Schema(description = "The content of the SMS message", example = "Your verification code is 123456")
    private String message;

    @Schema(description = "The ID of the client associated with this SMS", example = "101")
    private Long clientId;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isWhatsApp() {
        return isWhatsApp;
    }

    public void setWhatsApp(boolean isWhatsApp) {
        this.isWhatsApp = isWhatsApp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clienteId) {
        this.clientId = clienteId;
    }
}
