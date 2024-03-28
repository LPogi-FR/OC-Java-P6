package com.lpogifr.paymybuddy.controller;

import static com.lpogifr.paymybuddy.utils.AppUtils.asJson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lpogifr.paymybuddy.assembler.UserAssembler;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.UsersService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UsersController.class)
class UsersControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UsersService service;

  @MockBean
  private UserAssembler assembler;

  @Test
  void itShouldFindAllUsers() throws Exception {
    mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk());
    verify(service).findAll();
  }

  @Test
  void itShouldFindUserByEmail() throws Exception {
    mockMvc.perform(get("/users/email")).andDo(print()).andExpect(status().isOk());
    verify(service).findByEmail(any());
  }

  @Test
  void itShouldSave() throws Exception {
    UserModel model = UserModel
      .builder()
      .bankAccount(BankAccountModel.builder().build())
      .email("email")
      .id(1L)
      .password("password")
      .friendList(Collections.emptyList())
      .build();
    mockMvc
      .perform(post("/users").content(asJson(model)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isCreated());
    verify(service).save(any());
  }

  @Test
  void itShouldDelete() throws Exception {
    mockMvc.perform(get("/users/email")).andDo(print()).andExpect(status().isNoContent());
    verify(service).delete(any());
  }

  @Test
  void itShouldUpdate() throws Exception {
    mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk());
    verify(service).update(any());
  }

  @Test
  void itShouldAddFriend() throws Exception {
    mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk());
    verify(service).addFriend(anyLong(), any());
  }
}
