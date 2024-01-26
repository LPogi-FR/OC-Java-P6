package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.repository.UsersRepository;
import com.lpogifr.paymybuddy.service.UsersService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

  private final UsersRepository repository;

  @Override
  public List<UserEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public UserEntity findByEmail(String email) {
    return repository.findByEmail(email);
  }
}
