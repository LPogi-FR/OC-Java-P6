package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.Assembler;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.model.FriendModel;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.BankAccountService;
import com.lpogifr.paymybuddy.service.FriendService;
import com.lpogifr.paymybuddy.service.UsersService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UsersController {

  private final UsersService usersService;
  private final BankAccountService bankAccountService;
  private final FriendService friendService;
  private final Assembler assembler;

  @GetMapping("/users")
  public ResponseEntity<List<UserModel>> findAllUsers() {
    List<UserModel> response = assembler.userEntityToModel(usersService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/bankAccount")
  public ResponseEntity<List<BankAccountModel>> findAllBankAccount() {
    List<BankAccountModel> response = assembler.bankAccountEntityToModel(bankAccountService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/friend")
  public ResponseEntity<List<FriendModel>> findAllFriend() {
    List<FriendModel> response = assembler.friendEntityToModel(friendService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
