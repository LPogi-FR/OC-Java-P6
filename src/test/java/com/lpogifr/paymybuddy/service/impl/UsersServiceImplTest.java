package com.lpogifr.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import com.lpogifr.paymybuddy.PayMyBuddyAppTest;
import com.lpogifr.paymybuddy.PayMyBuddyApplication;
import com.lpogifr.paymybuddy.assembler.SenderAssembler;
import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.repository.SendersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = { PayMyBuddyAppTest.class })
@TestPropertySource(locations = { "classpath:application-test.properties" })
class SendersServiceImplTest {

  @Autowired
  private SendersServiceImpl service;

  @Autowired
  private SendersRepository senderRepository;

  @BeforeEach
  void setUp() {
    this.senderRepository.deleteAll();
    SenderEntity sender1 = SenderEntity.builder().id(1L).email("Test1").name("Didier").password("1234567890").build();
    this.senderRepository.save(sender1);
    SenderEntity sender2 = SenderEntity.builder().id(2L).email("Test2").name("Fernand").password("1234567890").build();
    this.senderRepository.save(sender2);
    SenderEntity sender3 = SenderEntity.builder().id(3L).email("Test3").name("Julie").password("1234567890").build();
    this.senderRepository.save(sender3);
  }

  @Test
  void itShouldFindAll() throws Exception {
    final var all = service.findAll();
    assertDoesNotThrow(() -> all);
    assertEquals(3, all.size());
    //assertEquals("ouioui@email.com", all.get(0).getEmail());
    //assertEquals("nonnon@email.com", all.get(1).getEmail());
  }

  @Test
  void itShouldFindByEmail() {
    final var email = assertDoesNotThrow(() -> service.findByEmail("Test1"));
    // assertEquals(1L, email.getId());
    assertEquals("Didier", email.getName());
    assertEquals("1234567890", email.getPassword());
    //verify(repository).findByEmail(anyString());
  }

  @Test
  void itShouldFindById() {
    final var id = assertDoesNotThrow(() -> service.findById(1L));
    assertEquals("Test1", id.getEmail());
    assertEquals("Didier", id.getName());
    assertEquals("1234567890", id.getPassword());
    //verify(repository).findById(anyLong());
  }

  @Test
  void itShouldSave() {
    // Need DB for Test
    SenderModel newSender = SenderModel.builder().email("Test4").name("Test4").password("Test4").build();
    assertDoesNotThrow(() -> service.save(newSender));
    final var all = service.findAll();
    assertEquals(4, all.size());
    assertEquals("Test4", all.get(3).getEmail());
    //verify(repository).save(any());
  }

  @Test
  void itShouldUpdate() {
    SenderModel newSender = SenderModel.builder().email("Test4").name("AZE").password("Test4").build();
    assertDoesNotThrow(() -> service.update(4L, newSender));
    final var all = service.findAll();
    assertEquals(4, all.size());
    assertEquals("Test4", all.get(3).getName());
    //verify(repository).save(any());
  }

  @Test
  void itShouldDelete() {
    assertDoesNotThrow(() -> service.delete("Test4"));
    final var all = service.findAll();
    assertEquals(3, all.size());
    //verify(repository).deleteByEmail(any());
  }

  @Test
  void itShouldFindOtherUSers() {
    final var otherSender = service.findOtherUSers(1L);
    assertEquals("Julie", otherSender.get(0).getName());
    //assertDoesNotThrow(() -> service.findOtherUSers(1L));
    //verify(repository).findOtheSender(any());
  }

  @Test
  void itShouldAddreceiver() {
    assertDoesNotThrow(() -> service.addreceiver(1L, 3L));
    assertEquals(1, service.findById(1L).getReceiverList().size());
  }
}
