package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import java.util.List;

public interface BankAccountService {
  /**
   * Find all BankAccount in Database
   * @return List<BankAccountEntity>
   */
  List<BankAccountEntity> findAll();

  BankAccountModel findById(Long id);

  BankAccountModel save(BankAccountModel newBackAccount);

  void deleteById(Long id);

  BankAccountModel update(Long id, BankAccountModel updatedBankAccount);

  double sendMoney(BankAccountModel bankAccount, double sentAmount);

  void receivceMoney(BankAccountModel bankAccount, double receiveAmount);
}
