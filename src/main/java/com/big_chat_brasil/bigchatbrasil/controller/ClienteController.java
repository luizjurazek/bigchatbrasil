package com.big_chat_brasil.bigchatbrasil.controller;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


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

    @PutMapping("/limite/{id}")
    public Client alterLimit(@PathVariable Long id, @RequestBody BigDecimal newLimit) {
        return clientService.alterCreditLimit(id, newLimit);
    }

    @PutMapping("/credito/{id}")
    public Client addCredit(@PathVariable Long id, @RequestBody BigDecimal value) {
        return clientService.addCredit(id, value);
    }
}
