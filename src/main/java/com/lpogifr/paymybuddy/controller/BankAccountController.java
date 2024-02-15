package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.Assembler;
import com.lpogifr.paymybuddy.dto.BankAccountDto;
import com.lpogifr.paymybuddy.dto.UsersDto;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.BankAccountService;
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
public class BankAccountController {

  private final BankAccountService bankAccountService;
  private final Assembler assembler;

  @GetMapping("/bankAccount")
  public ResponseEntity<List<BankAccountModel>> findAllBankAccount() {
    List<BankAccountModel> response = assembler.bankAccountEntityToModel(bankAccountService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  /* @PostMapping("/bankAccount")
  public ResponseEntity<BankAccountModel> save(@RequestBody final BankAccountDto dto) {
    final var response = assembler.bankAccountEntityToModel(bankAccountService.save(dto));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }*/
}
