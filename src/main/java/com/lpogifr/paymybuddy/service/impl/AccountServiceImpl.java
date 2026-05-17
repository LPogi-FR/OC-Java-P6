package com.lpogifr.paymybuddy.service.impl;

import static com.lpogifr.paymybuddy.utils.AppUtils.isBalancePositive;
import static com.lpogifr.paymybuddy.utils.AppUtils.isSentAmountPositiveAndNotNull;

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
  public double sendMoney(AccountModel account, double sentAmount) {
    if (isSentAmountPositiveAndNotNull(sentAmount)) {
      if (isBalancePositive(account.getBalance(), sentAmount)) {
        account.setBalance(account.getBalance() - sentAmount);
        update(account.getId(), account);
      } else {
        sentAmount = 0;
        System.out.println("Operation Impossible not enough found in account");
        /*JOptionPane.showMessageDialog(
          this,
          "Error in Transaction",
          "Operation Impossible not enough found in account",
          JOptionPane.ERROR_MESSAGE
        );*/
      }
    } else {
      sentAmount = 0;
      System.out.println("Operation Impossible sent amount negative or null");
      JOptionPane.showMessageDialog(
        null,
        "Error in Transaction",
        "Operation Impossible sent amount negative or null",
        JOptionPane.ERROR_MESSAGE
      );
    }
    // return new Exception()*/
    return sentAmount;
  }

  @Override
  public void receivceMoney(AccountModel account, double receiveAmount) {
    account.setBalance(account.getBalance() + receiveAmount);
    update(account.getId(), account);
  }
}
