package com.big_chat_brasil.bigchatbrasil.service;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.model.planType;
import com.big_chat_brasil.bigchatbrasil.repository.ClientRepository;
import com.big_chat_brasil.bigchatbrasil.validator.ClientValidator;
import com.big_chat_brasil.bigchatbrasil.dto.ErrorResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;
    private final ClientValidator clientValidator;

    public ClientService(ClientRepository clientRepository, ClientValidator clientValidator) {
        this.clientRepository = clientRepository;
        this.clientValidator = clientValidator;
    }

    public ResponseEntity<?> registerClient(Client client) {
        // Valida as informações do cliente
        List<ErrorResponse> errors = clientValidator.validate(client);

        if (!errors.isEmpty()) {
            // Retorna erros se existirem
            return ResponseEntity.badRequest().body(errors);
        }

        // Caso não haja erros, salva o cliente
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.ok(savedClient);
    }

    public List<Client> getClients() {
        return clientRepository.findAll(); 
    }
    
    public Client consultClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
    }

    public Client alterCreditLimit(Long id, BigDecimal newLimit) {
        Client Client = consultClient(id);
        if(Client.getPlan().equals(planType.PRE_PAGO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The client's plan does not allow credit limit increase");
        }
        Client.setCreditLimit(newLimit);
        return clientRepository.save(Client);
    }

    public Client addCredit(Long id, BigDecimal newCreditLimit) {
        Client Client = consultClient(id);
        if(Client.getPlan().equals(planType.POS_PAGO)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The client's plan does not allow credit limit increase");
        }
        Client.setCreditLimit(Client.getCreditLimit().add(newCreditLimit));
        return clientRepository.save(Client);
    }

    public Client alterPlan(Long id, planType newPlanType) {
        Client Client = consultClient(id);
        if (Client.getPlan() == newPlanType) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The new plan is the same as the current plan");        
        }
        Client.setPlan(newPlanType);
        return clientRepository.save(Client);
    }
}
