package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.dto.BankAccountDto;
import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.repository.BankAccountRepository;
import com.lpogifr.paymybuddy.service.BankAccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository repository;

  @Override
  public List<BankAccountEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public BankAccountEntity save(BankAccountDto dto) {
    final var bankAccount = new BankAccountEntity();
    bankAccount.setBalance(dto.getBalance());
    bankAccount.setBic(bankAccount.getBic());
    bankAccount.setIban(bankAccount.getIban());
    bankAccount.setId(bankAccount.getId());
    return repository.save(bankAccount);
  }
}
