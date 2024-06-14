package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UsersService {
  /**
   * Find all Users in Database
   * @return List<UserEntity>
   */
  List<UserEntity> findAll();
  UserEntity findByEmail(String email);
}
