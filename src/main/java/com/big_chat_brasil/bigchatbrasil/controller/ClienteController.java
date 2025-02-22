package com.big_chat_brasil.bigchatbrasil.controller;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.model.planType;
import com.big_chat_brasil.bigchatbrasil.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;

import com.big_chat_brasil.bigchatbrasil.dto.AlterClientLimitDTO;
import com.big_chat_brasil.bigchatbrasil.dto.ClientBalanceDTO;
import com.big_chat_brasil.bigchatbrasil.dto.AddClientCreditDTO;
import com.big_chat_brasil.bigchatbrasil.dto.AlterPlanTypeDTO;



@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Endpoints to manage clients")
public class ClienteController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public Client registerClient(@RequestBody Client client) {
        return clientService.registerClient(client);
    }

    @GetMapping
    public List<Client> getClientes() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public Client consultClient(@PathVariable Long id) {
        return clientService.consultClient(id);
    }

    @PutMapping("/limit/{id}")
    public Client alterLimit(
        @Parameter(description = "The ID of the client to which the credit limit will be set") 
        @PathVariable Long id, 

        @Parameter(description = "Object containing the new credit limit to be set", required = true) 
        @RequestBody AlterClientLimitDTO limitRequest) {  // Alterando para o DTO diretamente
        
        BigDecimal newLimit = limitRequest.getNewLimit();
        
        return clientService.alterCreditLimit(id, newLimit);
}


    @PutMapping("/credit/{id}")
    public Client addCredit(
        @Parameter(description = "The ID of the client to which the credit will be added") 
        @PathVariable Long id, 
        
        @Parameter(description = "Object containing the new credit balance to be added", required = true) 
        @RequestBody AddClientCreditDTO creditRequest) throws IOException {
        
        BigDecimal newBalanceCredit = creditRequest.getNewBalanceCredit();
        return clientService.addCredit(id, newBalanceCredit);
    }

    @GetMapping("/credit/balance/{id}")
    public ClientBalanceDTO consultClientBalance(
        @Parameter(description = "The ID of the client whose credit balance is being queried") 
        @PathVariable Long id) {
        
        Client client = clientService.consultClient(id);
        BigDecimal availableBalance = client.getCreditLimit().subtract(client.getUsedCredit());

        return new ClientBalanceDTO(client.getId(), availableBalance, client.getUsedCredit(), client.getCreditLimit());
    }

    @PutMapping("/alter-plan-type/{clientId}")
    public Client alterPlanType(
        @Parameter(description = "The ID of the client whose plan type will be updated") 
        @PathVariable Long clientId, 
        
        @Parameter(description = "Object containing the new plan type for the client", required = true) 
        @RequestBody AlterPlanTypeDTO payload) throws IOException { 
        
        planType newPlanType = payload.getNewPlanType();
        return clientService.alterPlan(clientId, newPlanType);
    }
}
