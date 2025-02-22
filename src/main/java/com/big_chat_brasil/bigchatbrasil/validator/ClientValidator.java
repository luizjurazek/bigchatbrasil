package com.big_chat_brasil.bigchatbrasil.validator;

import com.big_chat_brasil.bigchatbrasil.dto.ErrorResponse;
import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ClientValidator {

    private final ClientRepository clientRepository;

    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ErrorResponse> validate(Client client) {
        List<ErrorResponse> errors = new ArrayList<>();

        Client emailAlreadyInUse = clientRepository.findByEmail(client.getEmail());
        Client cpfAlreadyInUse = clientRepository.findByCpf(client.getCpf());
        Client cnpjAlreadyInUse = clientRepository.findByCnpj(client.getCnpj());
        Client phoneAlreadyInUse = clientRepository.findByPhone(client.getPhone());

        if (emailAlreadyInUse != null) {
            errors.add(new ErrorResponse("email", "Email already in use"));
        }
        // Verificar se os padroes estão corretos

        if (cpfAlreadyInUse != null) {
            errors.add(new ErrorResponse("cpf", "CPF already in use"));
        }
        if (cnpjAlreadyInUse != null) {
            errors.add(new ErrorResponse("cnpj", "CNPJ already in use"));
        }
        if (phoneAlreadyInUse != null) {
            errors.add(new ErrorResponse("phone", "Phone already in use"));
        }

        return errors;
    }
}
