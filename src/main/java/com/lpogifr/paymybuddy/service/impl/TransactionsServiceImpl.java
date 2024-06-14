package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.TransactionsAssembler;
import com.lpogifr.paymybuddy.entity.TransactionsEntity;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import com.lpogifr.paymybuddy.repository.TransactionsRepository;
import com.lpogifr.paymybuddy.service.TransactionsService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

  private TransactionsRepository repository;

  private TransactionsAssembler assembler;

  @Override
  public List<TransactionsEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public TransactionsModel save(TransactionsModel newTransactions) {
    repository.save(assembler.fromModelToEntity(newTransactions));
    return newTransactions;
  }

  @Override
  public List<TransactionsEntity> findByUserId(Long UserId) {
    return repository.findAll().stream().filter(p -> p.getUser().getId() == UserId).toList();
  }
}
