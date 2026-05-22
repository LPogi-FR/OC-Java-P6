package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.AccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
  List<AccountEntity> findAll();

  AccountEntity save(AccountEntity entity);

  @Query(value = "delete from SenderEntity where id = ?1")
  void deleteById(Long id);
}
