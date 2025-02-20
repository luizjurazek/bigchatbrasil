package com.big_chat_brasil.bigchatbrasil.service;

import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.model.SMS;
import com.big_chat_brasil.bigchatbrasil.repository.ClientRepository;
import com.big_chat_brasil.bigchatbrasil.repository.SMSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import com.big_chat_brasil.bigchatbrasil.model.planType;

@Service
public class SMSService {

    @Autowired
    private SMSRepository smsRepository;

    @Autowired
    private ClientRepository clientRepository;

    private BigDecimal costPerMessage = new BigDecimal("0.25");

    // Method to send a message
    public SMS sendMessage(SMS sms) {
        Client client = clientRepository.findById(sms.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        
        // Verificação de saldo para clientes pre-pago
        // Colocar o valor em uma variavel 
        if (client.getPlan().equals(planType.PRE_PAGO) && client.getBalanceCredit().compareTo(costPerMessage) >= 0) {
            client.setBalanceCredit(client.getBalanceCredit().subtract(costPerMessage));
            clientRepository.save(client);
        } else if (client.getPlan().equals(planType.POS_PAGO)) {
            // Registrar o consumo no limite de crédito
            if (client.getCreditLimit().compareTo(costPerMessage) >= 0) {
                client.setCreditLimit(client.getCreditLimit().subtract(costPerMessage));
                clientRepository.save(client);
            } else {
                throw new RuntimeException("Credit limit exceeded");
            }
        }
        
        return smsRepository.save(sms);
    }

    // Method to list all messages
    public List<SMS> getAllMessages() {
        return smsRepository.findAll();
    }

    // Method to check a message by id
    public SMS getMessageById(Long smsId) {
        return smsRepository.findById(smsId).orElseThrow(() -> new RuntimeException("Message not found"));
    }

    // Método para excluir uma mensagem
    public SMS deleteMessage(Long smsId) {
        SMS sms = smsRepository.findById(smsId).orElseThrow(() -> new RuntimeException("Message not found"));
        smsRepository.delete(sms);
        return sms;
    }

    // Method to list messages by client
    public List<SMS> getMessageByClient(Long clientId) {
        return smsRepository.findByClientId(clientId);
    }    
}


