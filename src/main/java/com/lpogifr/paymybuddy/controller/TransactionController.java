package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.TransactionsAssembler;
import com.lpogifr.paymybuddy.model.TransactionRequestModel;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.AccountService;
import com.lpogifr.paymybuddy.service.TransactionsService;
import com.lpogifr.paymybuddy.service.UsersService;
import java.time.LocalDateTime;
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
  private AccountService accountService;
  private UsersService usersService;

  @GetMapping("/transactions")
  public ResponseEntity<List<TransactionsModel>> findAllTransactions() {
    List<TransactionsModel> response = transactionsAssembler.fromEntityListToModelList(transactionsService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/transactions")
  public ResponseEntity<TransactionsModel> save(@RequestBody final TransactionRequestModel model) {
    if (model == null) {
      return ResponseEntity.badRequest().build();
    }
    final var moneyToRecieve = accountService.sendMoney(
      usersService.findById(model.getUserId()).getAccount(),
      model.getAmount()
    );
    accountService.receivceMoney(usersService.findById(model.getFriendId()).getAccount(), moneyToRecieve);
    UserModel userModel = usersService.findById(model.getUserId());
    UserModel friendModel = usersService.findById(model.getFriendId());
    final var response = TransactionsModel
      .builder()
      .user(userModel)
      .friend(friendModel)
      .execTime(LocalDateTime.now())
      .amount(model.getAmount())
      .build();
    transactionsService.save(response);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
