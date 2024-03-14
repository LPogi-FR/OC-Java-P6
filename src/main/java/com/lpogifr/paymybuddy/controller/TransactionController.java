package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.TransactionsAssembler;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import com.lpogifr.paymybuddy.service.BankAccountService;
import com.lpogifr.paymybuddy.service.TransactionsService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TransactionController {

  private TransactionsAssembler transactionsAssembler;
  private TransactionsService transactionsService;
  private BankAccountService bankAccountService;

  @GetMapping("/transactions")
  public ResponseEntity<List<TransactionsModel>> findAllTransactions() {
    List<TransactionsModel> response = transactionsAssembler.fromEntityListToModelList(transactionsService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/transactions")
  public ResponseEntity<TransactionsModel> save(@RequestBody final TransactionsModel model) {
    if (model == null) {
      return ResponseEntity.badRequest().build();
    }
    bankAccountService.receivceMoney(
      model.getFriend().getBankAccount(),
      bankAccountService.sendMoney(model.getUser().getBankAccount(), model.getAmount())
    );

    final var response = transactionsService.save(model);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
