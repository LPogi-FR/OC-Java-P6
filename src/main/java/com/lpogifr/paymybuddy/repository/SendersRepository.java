package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.SenderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SendersRepository extends JpaRepository<SenderEntity, Long> {
  List<SenderEntity> findAll();

  SenderEntity findByEmail(String email);

  SenderEntity save(SenderEntity entity);

  @Query(value = "delete from SenderEntity where email = ?1")
  void deleteByEmail(String email);

  SenderEntity findByName(String name);

  @Query(
    value = "select * from senders where id not in (select receiver_id from receiver where sender_id = ?1) AND id <> ?1",
    nativeQuery = true
  )
  List<SenderEntity> findOtheSender(Long senderId);
}
