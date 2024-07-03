package com.lpogifr.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import com.lpogifr.paymybuddy.PayMyBuddyAppTest;
import com.lpogifr.paymybuddy.assembler.BankAccountAssembler;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.repository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = { PayMyBuddyAppTest.class })
@TestPropertySource(locations = { "classpath:application-test.properties" })
class BankAccountServiceImplTest {

  @Autowired
  private BankAccountServiceImpl service;

  @Test
  void itShouldFindAll() {
    final var all = assertDoesNotThrow(() -> service.findAll());
    assertEquals(3, all.size());
  }

  @Test
  void itShouldFindById() {
    final var id = assertDoesNotThrow(() -> service.findById(1L));
    assertEquals("123", id.getBic());
  }

  @Test
  void itShouldSave() {
    assertDoesNotThrow(() -> service.save(BankAccountModel.builder().build()));
  }

  @Test
  void itShouldUpdate() {
    // Add a bankAccount in DB
    assertDoesNotThrow(() -> service.update(1L, BankAccountModel.builder().build()));
  }

  @Test
  void itShouldDeleteById() {
    assertDoesNotThrow(() -> service.deleteById(1L));
  }

  @Test
  void itShouldSendMoney() {
    //Need DB
    assertDoesNotThrow(() -> service.sendMoney(service.findById(1L), 200));
    //verify(service).update(anyLong(), any());
  }

  @Test
  void itShouldReceivceMoney() {
    //Need DB
    assertDoesNotThrow(() -> service.receivceMoney(BankAccountModel.builder().balance(300D).build(), 200));
    //verify(service).update(anyLong(), any());
  }
}
