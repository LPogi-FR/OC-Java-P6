package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.model.UserModel;
import java.util.List;

public interface UsersService {
  /**
   * Find all Users in Database
   * @return List<UserEntity>
   */
  List<UserModel> findAll();
  UserModel findByEmail(String email);

  UserModel findByName(String name);

  UserModel findById(Long id);

  UserModel save(UserModel newUser);

  void delete(String email);

  UserModel update(UserModel updatedUser);

  UserModel addFriend(Long id, Long friendId);

  List<UserModel> findOtherUSers(Long userId);
}
