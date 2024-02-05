package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.FriendEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, Long> {
  List<FriendEntity> findAll();
}
