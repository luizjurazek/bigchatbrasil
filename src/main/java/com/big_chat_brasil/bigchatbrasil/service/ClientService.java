package com.big_chat_brasil.bigchatbrasil.service;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.model.planType;
import com.big_chat_brasil.bigchatbrasil.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
        return ClientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    public Client alterCreditLimit(Long id, BigDecimal newLimit) {
        Client Client = consultClient(id);
        Client.setCreditLimit(newLimit);
        return ClientRepository.save(Client);
    }

    public Client addCredit(Long id, BigDecimal newCreditLimit) {
        Client Client = consultClient(id);
        Client.setCreditLimit(Client.getCreditLimit().add(newCreditLimit));
        return ClientRepository.save(Client);
    }

    public Client alterPlan(Long id, planType newPlanType) {
        Client Client = consultClient(id);
        if (Client.getPlan() == newPlanType) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The new plan is the same as the current plan");        
        }
        Client.setPlan(newPlanType);
        return ClientRepository.save(Client);
    }
}
