package com.big_chat_brasil.bigchatbrasil.repository;

import com.big_chat_brasil.bigchatbrasil.model.SMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SMSRepository extends JpaRepository<SMS, Long> {
  List<SMS> findByClientId(Long clientId);
  List<SMS> findAllByOrderByIdDesc();
  List<SMS> findByClientIdOrderByIdDesc(Long clientId);
}
