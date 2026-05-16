package com.lpogifr.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

import com.lpogifr.paymybuddy.PayMyBuddyAppTest;
import com.lpogifr.paymybuddy.PayMyBuddyApplication;
import com.lpogifr.paymybuddy.assembler.UserAssembler;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.repository.UsersRepository;
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
class UsersServiceImplTest {

  @Autowired
  private UsersServiceImpl service;

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
    assertDoesNotThrow(() -> service.findByEmail("AZE"));
    //verify(repository).findByEmail(anyString());
  }

  @Test
  void itShouldFindById() {
    assertDoesNotThrow(() -> service.findById(1L));
    //verify(repository).findById(anyLong());
  }

  @Test
  void itShouldSave() {
    // Need DB for Test
    assertDoesNotThrow(() -> service.save(UserModel.builder().build()));
    //verify(repository).save(any());
  }

  @Test
  void itShouldUpdate() {
    assertDoesNotThrow(() -> service.update(UserModel.builder().build()));
    //verify(repository).save(any());
  }

  @Test
  void itShouldDelete() {
    assertDoesNotThrow(() -> service.delete("gdzzf"));
    //verify(repository).deleteByEmail(any());
  }

  @Test
  void itShouldAddFriend() {
    assertDoesNotThrow(() -> service.addFriend(1L, 3L));
    assertEquals(3, service.findById(1L).getFriendList().size());
  }

  @Test
  void itShouldFindOtherUSers() {
    final var otherUser = service.findOtherUSers(1L);
    assertEquals(3L, otherUser.get(0).getId());
    //assertDoesNotThrow(() -> service.findOtherUSers(1L));
    //verify(repository).findOtheUser(any());
  }
}
