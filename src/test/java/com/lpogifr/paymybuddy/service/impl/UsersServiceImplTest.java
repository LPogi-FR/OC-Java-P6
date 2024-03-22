package com.lpogifr.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.lpogifr.paymybuddy.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class UsersServiceImplTest {

  @Autowired
  private UsersService service;

  @Test
  void itShouldFindAll() throws Exception {
    final var all = service.findAll();
    assertDoesNotThrow(() -> all);
    assertEquals(2, all.size());
    assertEquals("ouioui@email.com", all.get(0).getEmail());
    assertEquals("nonnon@email.com", all.get(1).getEmail());
  }

  @Test
  void itShouldFindByEmail() {}

  @Test
  void itShouldFindById() {}

  @Test
  void itShouldSave() {}

  @Test
  void itShouldDelete() {}

  @Test
  void itShouldUpdate() {}

  @Test
  void itShouldAddFriend() {}
}
