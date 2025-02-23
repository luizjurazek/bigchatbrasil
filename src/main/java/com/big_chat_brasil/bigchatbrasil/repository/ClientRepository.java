package com.big_chat_brasil.bigchatbrasil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.big_chat_brasil.bigchatbrasil.model.Client;
import com.big_chat_brasil.bigchatbrasil.model.SMS;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Client findByCnpj(String cnpj);
    Client findByCpf(String cpf);
    Client findByPhone(String phone);
    List<Client> findAllByOrderByIdDesc();

}
