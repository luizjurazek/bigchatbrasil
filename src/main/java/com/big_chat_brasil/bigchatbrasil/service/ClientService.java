package com.big_chat_brasil.bigchatbrasil.service;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository ClientRepository;

    public Client registerClient(Client Client) {
        return ClientRepository.save(Client);
    }

    public List<Client> getClients() {
        return ClientRepository.findAll(); 
    }
    
    public Client consultClient(Long id) {
        return ClientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public Client alterCreditLimit(Long id, BigDecimal newLimit) {
        Client Client = consultClient(id);
        Client.setCreditLimit(newLimit);
        return ClientRepository.save(Client);
    }

    public Client addCredit(Long id, BigDecimal newBalanceCredit) {
        Client Client = consultClient(id);
        Client.setBalanceCredit(Client.getBalanceCredit().add(newBalanceCredit));
        return ClientRepository.save(Client);
    }
}
