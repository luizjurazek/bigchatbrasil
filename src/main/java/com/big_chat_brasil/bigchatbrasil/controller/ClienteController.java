package com.big_chat_brasil.bigchatbrasil.controller;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.model.planType;
import com.big_chat_brasil.bigchatbrasil.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import com.big_chat_brasil.bigchatbrasil.dto.AlterClientLimitDTO;
import com.big_chat_brasil.bigchatbrasil.dto.ClientBalanceDTO;
import com.big_chat_brasil.bigchatbrasil.dto.AddClientCreditDTO;
import com.big_chat_brasil.bigchatbrasil.dto.AlterPlanTypeDTO;


@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Endpoints to manage clients")
public class ClienteController {
    
    @Autowired
    private final ClientService clientService;
    public ClienteController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(
        summary = "Register a new client",
        description = "This endpoint registers a new client by providing all necessary details like name, contact, etc."
    )
    @PostMapping("/register")
    public ResponseEntity<?> registerClient(
        @Parameter(description = "Client object containing the client's information", required = true)
        @RequestBody Client client) {
        return clientService.registerClient(client);
    }

    @Operation(
        summary = "Get all clients",
        description = "This endpoint retrieves a list of all registered clients."
    )
    @GetMapping
    public List<Client> getClientes() {
        return clientService.getClients();
    }

    @Operation(
        summary = "Consult a client by ID",
        description = "This endpoint retrieves the details of a client by their unique ID."
    )
    @GetMapping("/{clientId}")
    public Client consultClient(
        @Parameter(description = "The ID of the client to be retrieved", required = true)
        @PathVariable Long clientId) {
        return clientService.consultClient(clientId);
    }

    @Operation(
        summary = "Update a client's credit limit",
        description = "This endpoint allows updating the credit limit for a specified client."
    )
    @PutMapping("/update-limit/{clientId}")
    public Client alterLimit(
        @Parameter(description = "The ID of the client whose credit limit will be updated", required = true)
        @PathVariable Long clientId,

        @Parameter(description = "Object containing the new credit limit", required = true)
        @RequestBody AlterClientLimitDTO limitRequest) {
        
        BigDecimal newLimit = limitRequest.getNewLimit();
        return clientService.alterCreditLimit(clientId, newLimit);
    }

    @Operation(
        summary = "Add credit to a client's account",
        description = "This endpoint adds a specified amount of credit to a client's account."
    )
    @PutMapping("/add-credit-limit/{clientId}")
    public Client addCredit(
        @Parameter(description = "The ID of the client to whom credit will be added", required = true)
        @PathVariable Long clientId,

        @Parameter(description = "Object containing the new credit balance to be added", required = true)
        @RequestBody AddClientCreditDTO creditRequest) {
        
        BigDecimal newBalanceCredit = creditRequest.getNewBalanceCredit();
        return clientService.addCredit(clientId, newBalanceCredit);
    }

    @Operation(
        summary = "Consult a client's credit balance",
        description = "This endpoint retrieves the current available balance and the used credit of a client."
    )
    @GetMapping("/credit-balance/{clientId}")
    public ClientBalanceDTO consultClientBalance(
        @Parameter(description = "The ID of the client whose credit balance is being queried", required = true)
        @PathVariable Long clientId) {
        
        Client client = clientService.consultClient(clientId);
        BigDecimal availableBalance = client.getCreditLimit().subtract(client.getUsedCredit());

        return new ClientBalanceDTO(client.getId(), availableBalance, client.getUsedCredit(), client.getCreditLimit());
    }

    @Operation(
        summary = "Update a client's plan type",
        description = "This endpoint allows updating the plan type for a specified client."
    )
    @PutMapping("/alter-plan-type/{clientId}")
    public Client alterPlanType(
        @Parameter(description = "The ID of the client whose plan type will be updated", required = true)
        @PathVariable Long clientId,

        @Parameter(description = "Object containing the new plan type for the client", required = true)
        @RequestBody AlterPlanTypeDTO payload) {
        
        planType newPlanType = payload.getNewPlanType();
        return clientService.alterPlan(clientId, newPlanType);
    }
}