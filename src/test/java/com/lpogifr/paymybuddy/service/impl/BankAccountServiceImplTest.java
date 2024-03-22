package com.lpogifr.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import com.lpogifr.paymybuddy.assembler.BankAccountAssembler;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@ExtendWith(MockitoExtension.class)
class BankAccountServiceImplTest {

  @InjectMocks
  private BankAccountServiceImpl service;

  @Mock
  private BankAccountRepository repository;

  @Mock
  private BankAccountAssembler assembler;

  @Test
  void itShouldFindAll() {
    assertDoesNotThrow(() -> service.findAll());
    verify(repository).findAll();
  }

  @Test
  void itShouldFindById() {
    assertDoesNotThrow(() -> service.findById(1L));
    verify(repository).findById(anyLong());
  }

  @Test
  void itShouldSave() {
    assertDoesNotThrow(() -> service.save(BankAccountModel.builder().build()));
    verify(repository).save(any());
  }

  @Test
  void itShouldDeleteById() {
    assertDoesNotThrow(() -> service.deleteById(1L));
    verify(repository).deleteById(any());
  }

  @Test
  void itShouldUpdate() {
    //assertDoesNotThrow();
  }

  @Test
  void itShouldSendMoney() {}

  @Test
  void itShouldReceivceMoney() {}
}
