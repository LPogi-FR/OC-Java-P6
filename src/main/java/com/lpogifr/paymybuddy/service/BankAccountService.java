package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.dto.BankAccountDto;
import com.lpogifr.paymybuddy.dto.UsersDto;
import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import java.util.List;

public interface BankAccountService {
  /**
   * Find all BankAccount in Database
   * @return List<BankAccountEntity>
   */
  List<BankAccountEntity> findAll();

  BankAccountEntity save(BankAccountDto dto);
}
