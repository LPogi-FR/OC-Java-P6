package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.BankAccountAssembler;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.service.BankAccountService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class BankAccountController {

  private final BankAccountService bankAccountService;
  private final BankAccountAssembler assembler;

  @GetMapping("/bankAccount")
  public ResponseEntity<List<BankAccountModel>> findAllBankAccount() {
    List<BankAccountModel> response = assembler.fromEntityListToModelList(bankAccountService.findAll());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/bankAccount")
  public ResponseEntity<BankAccountModel> save(@RequestBody final BankAccountModel model) {
    if (model == null) {
      return ResponseEntity.badRequest().build();
    }
    final var response = bankAccountService.save(model);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping("/bankAccount/{id}")
  public ResponseEntity<BankAccountModel> update(@PathVariable Long id, @RequestBody final BankAccountModel model) {
    if (bankAccountService.findById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    final var response = bankAccountService.update(id, model);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/bankAccount/{id}")
  public ResponseEntity<BankAccountModel> delete(@PathVariable(name = "id") Long id) {
    if (bankAccountService.findById(id) == null) {
      return ResponseEntity.notFound().build();
    }
    bankAccountService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
