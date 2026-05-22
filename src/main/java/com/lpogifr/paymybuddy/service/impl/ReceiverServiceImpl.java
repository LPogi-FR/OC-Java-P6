package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.repository.SendersRepository;
import com.lpogifr.paymybuddy.service.ReceiverService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {

  private final SendersRepository repository;

  @Override
  public List<SenderEntity> findAll() {
    return repository.findAll();
  }
}
