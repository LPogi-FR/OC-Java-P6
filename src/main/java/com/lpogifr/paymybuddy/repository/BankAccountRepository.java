package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
  List<BankAccountEntity> findAll();

  BankAccountEntity save(BankAccountEntity entity);

  @Query(value = "delete from UserEntity where id = ?1")
  void deleteById(Long id);
}
