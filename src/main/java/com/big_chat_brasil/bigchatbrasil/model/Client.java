package com.big_chat_brasil.bigchatbrasil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.big_chat_brasil.bigchatbrasil.utils.BigDecimalDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Representation of a client in the system, containing personal and financial information.")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique client ID", example = "1", required = true)
    private Long id;

    @Schema(description = "Full name of the client", example = "John Doe", required = true)
    private String name;

    @Schema(description = "Email address of the client", example = "john.doe@email.com", required = true)
    private String email;

    @Schema(description = "Client's phone number", example = "(11) 91234-5678", required = false)
    private String phone;

    @Schema(description = "Client's CPF number", example = "123.456.789-00", required = false)
    private String cpf;

    @Schema(description = "Client's CNPJ number", example = "12.345.678/0001-99", required = false)
    private String cnpj;

    @Schema(description = "Name of the client's company", example = "XYZ Ltd.", required = false)
    private String companyName;

    @Schema(description = "Client's plan type", example = "PRE_PAGO", required = true, 
            allowableValues = {"PRE_PAGO", "POS_PAGO"})
    private planType plan; // "PRE_PAGO" or "POS_PAGO"

    @JsonDeserialize(using = BigDecimalDeserializer.class)
    @Schema(description = "Credit used by the client", example = "150.50", required = false)
    private BigDecimal usedCredit;

    @JsonDeserialize(using = BigDecimalDeserializer.class)
    @Schema(description = "Credit limit available for the client", example = "500.00", required = false)
    private BigDecimal creditLimit;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getUsedCredit() {
        return usedCredit;
    }

    public void setUsedCredit(BigDecimal usedCredit) {
        this.usedCredit = usedCredit;
    }

    public planType getPlan() {
        return plan;
    }

    public void setPlan(planType plan) {
        this.plan = plan;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
