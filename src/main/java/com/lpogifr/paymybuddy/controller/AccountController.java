package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.AccountAssembler;
import com.lpogifr.paymybuddy.model.AccountModel;
import com.lpogifr.paymybuddy.service.AccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class AccountController {

  private final AccountService accountService;
  private final AccountAssembler assembler;

  @GetMapping("/account")
  public ResponseEntity<List<AccountModel>> findAllAccount() {
    List<AccountModel> response = assembler.fromEntityListToModelList(accountService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/account")
  public ResponseEntity<AccountModel> save(@RequestBody final AccountModel model) {
    if (model == null) {
      return ResponseEntity.badRequest().build();
    }
    final var response = accountService.save(model);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/account/{id}")
  public ResponseEntity<AccountModel> update(@PathVariable Long id, @RequestBody final AccountModel model) {
    if (accountService.findById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    final var response = accountService.update(id, model);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/account/{id}")
  public ResponseEntity<AccountModel> delete(@PathVariable(name = "id") Long id) {
    if (accountService.findById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    accountService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
