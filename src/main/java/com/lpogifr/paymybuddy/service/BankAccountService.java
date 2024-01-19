package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import java.util.List;

public interface BankAccountService {
  /**
   * Find all BankAccount in Database
   * @return List<BankAccountEntity>
   */
  List<BankAccountEntity> findAll();
}
