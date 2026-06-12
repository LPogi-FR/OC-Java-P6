package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.ReceiverEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends JpaRepository<ReceiverEntity, Long> {
  List<ReceiverEntity> findAll();
}
