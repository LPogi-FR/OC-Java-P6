package com.lpogifr.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.lpogifr.paymybuddy.model.TransactionsModel;
import com.lpogifr.paymybuddy.repository.TransactionsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionsServiceImplTest {

  @InjectMocks
  private TransactionsServiceImpl service;

  @Mock
  private TransactionsRepository repository;

  @Test
  void itShouldFindAll() {
    assertDoesNotThrow(() -> service.findAll());
    verify(repository).findAll();
  }

  @Test
  void itShouldSave() {
    assertDoesNotThrow(() -> service.save(TransactionsModel.builder().build()));
    verify(repository).save(any());
  }

  @Test
  void itShouldFindByUserId() {
    assertDoesNotThrow(() -> service.findByUserId(1L));
    verify(repository).findAll();
  }
}
