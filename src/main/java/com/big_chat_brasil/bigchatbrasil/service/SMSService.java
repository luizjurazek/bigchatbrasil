package com.big_chat_brasil.bigchatbrasil.service;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.model.SMS;
import com.big_chat_brasil.bigchatbrasil.repository.ClientRepository;
import com.big_chat_brasil.bigchatbrasil.repository.SMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SMSService {

    @Autowired
    private SMSRepository smsRepository;

    @Autowired
    private ClientRepository clientRepository;

    private BigDecimal costPerMessage = new BigDecimal("0.25");

    // Method to send a message
    public SMS sendMessage(SMS sms) {
        Client client = clientRepository.findById(sms.getClient().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        // Both type of plan use CreditLimit to check if the user has enough balance
        // if plan is pre-pago, the creditLimit is the value alocated to use
        // if plan is pos-pago, the creditLimit is the max value that the user can use
        boolean hasBalance = client.getCreditLimit().subtract(client.getUsedCredit()).compareTo(costPerMessage) < 0;

        if (hasBalance) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User doesn't have enough balance");
        }

        client.setUsedCredit(client.getUsedCredit().add(costPerMessage));
        clientRepository.save(client);
        sms.setClient(client);

        return smsRepository.save(sms);
    }

    // Method to list all messages
    public List<SMS> getAllMessages() {
        return smsRepository.findAllByOrderByIdDesc();
    }

    // Method to check a message by id
    public SMS getMessageById(Long smsId) {
        return smsRepository.findById(smsId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
    }

    // Method to delete a message
    public SMS deleteMessage(Long smsId) {
        SMS sms = smsRepository.findById(smsId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found"));
        smsRepository.delete(sms);
        return sms;
    }

    // Method to list messages by client
    public List<SMS> getMessageByClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new  ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));
        return smsRepository.findByClientOrderByIdDesc(client);
    }    
}


