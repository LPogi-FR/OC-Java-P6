package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.TransactionsAssembler;
import com.lpogifr.paymybuddy.entity.TransactionsEntity;
import com.lpogifr.paymybuddy.exception.FundNotEnoughException;
import com.lpogifr.paymybuddy.exception.InvalidAmountException;
import com.lpogifr.paymybuddy.front.form.TransactionForm;
import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import com.lpogifr.paymybuddy.repository.TransactionsRepository;
import com.lpogifr.paymybuddy.service.AccountService;
import com.lpogifr.paymybuddy.service.SendersService;
import com.lpogifr.paymybuddy.service.TransactionsService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

  private TransactionsRepository repository;

  private TransactionsAssembler assembler;

  private SendersService sendersService;

  private AccountService accountService;

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
  public List<TransactionsEntity> findBySenderId(Long SenderId) {
    return repository.findAll().stream().filter(p -> p.getSender().getId() == SenderId).toList();
  }

  @Override
  public void createNewTransaction(TransactionForm transactionForm) {
    SenderModel sender = sendersService.findById(transactionForm.getSenderId());
    SenderModel receiver = sendersService.findById(transactionForm.getReceiverId());

    if (transactionForm.getAmount() == 0) {
      throw new InvalidAmountException("Transaction amount invalid");
    } else if (sender.getAccount().getBalance() - transactionForm.getAmount() < 0) {
      throw new FundNotEnoughException("Not enough fund in account");
    } else {
      accountService.sendMoney(sender.getAccount(), transactionForm.getAmount());
      accountService.receivceMoney(receiver.getAccount(), transactionForm.getAmount());
      TransactionsModel newTransaction = TransactionsModel
        .builder()
        .sender(sender)
        .receiver(receiver)
        .execTime(LocalDateTime.now())
        .amount(transactionForm.getAmount())
        .description(transactionForm.getDescription())
        .build();
      save(newTransaction);
    }
  }
}
