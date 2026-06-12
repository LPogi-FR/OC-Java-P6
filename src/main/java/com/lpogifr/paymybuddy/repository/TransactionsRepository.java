package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.TransactionsEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {
  List<TransactionsEntity> findAll();

  TransactionsEntity findByExecTime(LocalDateTime execTime);

  TransactionsEntity save(TransactionsEntity entity);

  List<TransactionsEntity> findTransactionsEntitiesBySenderId(Long senderId);
}
