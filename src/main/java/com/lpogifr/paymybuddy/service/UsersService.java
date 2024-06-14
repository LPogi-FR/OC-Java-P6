package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.dto.UsersDto;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import java.util.List;
import java.util.Optional;

public interface UsersService {
  /**
   * Find all Users in Database
   * @return List<UserEntity>
   */
  List<UserEntity> findAll();
  UserEntity findByEmail(String email);

  UserEntity save(UsersDto dto);

  void delete(String email);
}
