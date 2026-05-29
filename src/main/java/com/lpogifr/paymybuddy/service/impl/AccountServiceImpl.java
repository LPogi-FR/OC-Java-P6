package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.AccountAssembler;
import com.lpogifr.paymybuddy.entity.AccountEntity;
import com.lpogifr.paymybuddy.model.AccountModel;
import com.lpogifr.paymybuddy.repository.AccountRepository;
import com.lpogifr.paymybuddy.service.AccountService;
import java.util.List;
import java.util.Optional;
import javax.swing.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository repository;
  private final AccountAssembler assembler;

  @Override
  public List<AccountEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public AccountModel findById(Long id) {
    Optional<AccountEntity> entityOptional = repository.findById(id);
    return entityOptional.map(assembler::fromEntityToModel).orElse(null);
  }

  @Override
  public AccountModel save(AccountModel model) {
    repository.save(assembler.fromModelToEntity(model));
    return model;
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  @Override
  public AccountModel update(Long id, AccountModel updatedAccount) {
    Optional<AccountEntity> entity = repository.findById(id);
    entity.ifPresentOrElse(
      p -> {
        p.setBalance(updatedAccount.getBalance());
        repository.save(p);
      },
      () -> System.out.println("Account Not Found")
    );
    return assembler.fromEntityToModel(repository.findById(id).orElse(null));
  }

  @Override
  public void sendMoney(AccountModel account, double sentAmount) {
    account.setBalance(account.getBalance() - sentAmount);
    update(account.getId(), account);
  }

  @Override
  public void receivceMoney(AccountModel account, double receiveAmount) {
    account.setBalance(account.getBalance() + receiveAmount);
    update(account.getId(), account);
  }
}
