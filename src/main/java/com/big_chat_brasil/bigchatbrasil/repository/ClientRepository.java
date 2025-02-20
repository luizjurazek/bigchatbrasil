package com.big_chat_brasil.bigchatbrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.big_chat_brasil.bigchatbrasil.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Client findByCnpj(String cnpj);
}
