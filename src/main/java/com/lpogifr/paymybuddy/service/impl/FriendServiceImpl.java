package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.entity.FriendEntity;
import com.lpogifr.paymybuddy.repository.FriendRepository;
import com.lpogifr.paymybuddy.service.FriendService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendServiceImpl implements FriendService {

  private final FriendRepository repository;

  @Override
  public List<FriendEntity> findAll() {
    return repository.findAll();
  }
}
