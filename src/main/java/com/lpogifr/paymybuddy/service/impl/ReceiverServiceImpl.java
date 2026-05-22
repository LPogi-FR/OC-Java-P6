package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.repository.UsersRepository;
import com.lpogifr.paymybuddy.service.ReceiverService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {

  private final UsersRepository repository;

  @Override
  public List<UserEntity> findAll() {
    return repository.findAll();
  }
}
