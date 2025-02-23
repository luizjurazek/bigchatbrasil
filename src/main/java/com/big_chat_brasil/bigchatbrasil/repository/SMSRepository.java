package com.big_chat_brasil.bigchatbrasil.repository;

import com.big_chat_brasil.bigchatbrasil.model.SMS;
import com.big_chat_brasil.bigchatbrasil.model.Client;  // Importando o Client
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SMSRepository extends JpaRepository<SMS, Long> {
  // Utilizando o relacionamento com Client diretamente
  List<SMS> findByClient(Client client);
  
  // Ordenando todos os SMS por ID em ordem decrescente
  List<SMS> findAllByOrderByIdDesc();
  
  // Utilizando Client como parâmetro e ordenando por ID decrescente
  List<SMS> findByClientOrderByIdDesc(Client client);
}
