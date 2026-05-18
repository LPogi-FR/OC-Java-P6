package com.lpogifr.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.lpogifr.paymybuddy.PayMyBuddyAppTest;
import com.lpogifr.paymybuddy.model.AccountModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = { PayMyBuddyAppTest.class })
@TestPropertySource(locations = { "classpath:application-test.properties" })
class AccountServiceImplTest {

  @Autowired
  private AccountServiceImpl service;

  @Test
  void itShouldFindAll() {
    final var all = assertDoesNotThrow(() -> service.findAll());
    assertEquals(3, all.size());
  }

  @Test
  void itShouldFindById() {
    final var id = assertDoesNotThrow(() -> service.findById(1L));
    assertEquals(400, id.getBalance());
  }

  @Test
  void itShouldSave() {
    assertDoesNotThrow(() -> service.save(AccountModel.builder().build()));
  }

  @Test
  void itShouldUpdate() {
    assertDoesNotThrow(() -> service.update(1L, AccountModel.builder().build()));
  }

  @Test
  void itShouldDeleteById() {
    assertDoesNotThrow(() -> service.deleteById(1L));
  }

  @Test
  void itShouldSendMoney() {
    assertDoesNotThrow(() -> service.sendMoney(service.findById(1L), 200));
  }

  @Test
  void itShouldReceivceMoney() {
    assertDoesNotThrow(() -> service.receivceMoney(AccountModel.builder().balance(300D).build(), 200));
  }
}
