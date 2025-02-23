package com.big_chat_brasil.bigchatbrasil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonBackReference; 

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

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "client_id", referencedColumnName = "id") 
    private Client client;  // Renomeado para 'client' para refletir a relação correta

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

    public Client getClient() {  // Alterado para usar 'client' em vez de 'clientId'
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

