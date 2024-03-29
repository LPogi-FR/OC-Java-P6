package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
  List<UserEntity> findAll();
}
