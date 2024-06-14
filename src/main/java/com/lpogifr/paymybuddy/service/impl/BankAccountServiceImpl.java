package com.lpogifr.paymybuddy.service.impl;

import static com.lpogifr.paymybuddy.utils.AppUtils.isBalancePositive;
import static com.lpogifr.paymybuddy.utils.AppUtils.isSentAmountPositiveAndNotNull;

import com.lpogifr.paymybuddy.assembler.BankAccountAssembler;
import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.repository.BankAccountRepository;
import com.lpogifr.paymybuddy.service.BankAccountService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

  private final BankAccountRepository repository;
  private final BankAccountAssembler assembler;

  @Override
  public List<BankAccountEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public BankAccountModel findById(Long id) {
    Optional<BankAccountEntity> entityOptional = repository.findById(id);
    return entityOptional.map(assembler::fromEntityToModel).orElse(null);
  }

  @Override
  public BankAccountModel save(BankAccountModel model) {
    repository.save(assembler.fromModelToEntity(model));
    return model;
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  @Override
  public BankAccountModel update(Long id, BankAccountModel updatedBankAccount) {
    Optional<BankAccountEntity> entity = repository.findById(id);
    entity.ifPresentOrElse(
      p -> {
        p.setBalance(updatedBankAccount.getBalance());
        p.setIban(updatedBankAccount.getIban());
        p.setBic(updatedBankAccount.getBic());
        repository.save(p);
      },
      () -> System.out.println("BankAccount Not Found")
    );
    return assembler.fromEntityToModel(repository.findById(id).orElse(null));
  }

  @Override
  public double sendMoney(BankAccountModel bankAccount, double sentAmount) {
    if (!isSentAmountPositiveAndNotNull(sentAmount) || !isBalancePositive(bankAccount.getBalance(), sentAmount)) {
      System.out.println("Operation Impossible");
      // return new Exception()
    }
    bankAccount.setBalance(bankAccount.getBalance() - sentAmount);
    update(bankAccount.getId(), bankAccount);
    return sentAmount;
  }

  @Override
  public void receivceMoney(BankAccountModel bankAccount, double receiveAmount) {
    bankAccount.setBalance(bankAccount.getBalance() + receiveAmount);
    update(bankAccount.getId(), bankAccount);
  }
}
