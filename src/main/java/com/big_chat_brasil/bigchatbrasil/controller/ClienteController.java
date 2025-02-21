package com.big_chat_brasil.bigchatbrasil.controller;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import com.big_chat_brasil.bigchatbrasil.dto.AlterClientLimitDTO;
import com.big_chat_brasil.bigchatbrasil.dto.AddClientCreditDTO;


@RestController
@RequestMapping("/clients")
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
    public Client alterLimit(@PathVariable Long id, @RequestBody String json) throws IOException {
        // Create a instace of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Deserialize JSON for DTO AlterClientLimit
        AlterClientLimitDTO limitRequest = objectMapper.readValue(json, AlterClientLimitDTO.class);
        
        // Acess the new limit
        BigDecimal newLimit = limitRequest.getNewLimit();
        
        return clientService.alterCreditLimit(id, newLimit);
    }

    @PutMapping("/credit/{id}")
    public Client addCredit(@PathVariable Long id, @RequestBody String json) throws IOException {
        // Create a instace of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Deserialize JSON for DTO AlterClientLimit
        AddClientCreditDTO addCreditRequest = objectMapper.readValue(json, AddClientCreditDTO.class);
        
        // Acess the new limit
        BigDecimal newBalanceCredit = addCreditRequest.getNewBalanceCredit();

        return clientService.addCredit(id, newBalanceCredit);
    }
}
