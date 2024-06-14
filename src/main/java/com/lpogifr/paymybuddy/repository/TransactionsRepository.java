package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.TransactionsEntity;
import com.lpogifr.paymybuddy.entity.UserEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {
  List<TransactionsEntity> findAll();

  TransactionsEntity findByExecTime(LocalDateTime execTime);

  TransactionsEntity save(TransactionsEntity entity);

  List<TransactionsEntity> findATransactionsEntitiesByUserId(Long userId);
}
