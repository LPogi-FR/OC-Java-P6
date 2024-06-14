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
  List<UserModel> findAll();
  UserModel findByEmail(String email);

  UserModel findById(Long id);

  UserModel save(UserModel newUser);

  void delete(String email);

  UserModel update(UserModel updatedUser);
}
