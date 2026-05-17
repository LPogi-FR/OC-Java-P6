package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.entity.AccountEntity;
import com.lpogifr.paymybuddy.model.AccountModel;
import java.util.List;

public interface AccountService {
  /**
   * Find all Account in Database
   * @return List<AccountEntity>
   */
  List<AccountEntity> findAll();

  AccountModel findById(Long id);

  AccountModel save(AccountModel newBackAccount);

  void deleteById(Long id);

  AccountModel update(Long id, AccountModel updatedAccount);

  double sendMoney(AccountModel account, double sentAmount);

  void receivceMoney(AccountModel account, double receiveAmount);
}
