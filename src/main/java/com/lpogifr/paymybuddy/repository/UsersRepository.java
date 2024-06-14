package com.lpogifr.paymybuddy.repository;

import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
  List<UserEntity> findAll();

  UserEntity findByEmail(String email);
  UserEntity save(UserEntity entity);
  void delete(UserEntity entity);
}
