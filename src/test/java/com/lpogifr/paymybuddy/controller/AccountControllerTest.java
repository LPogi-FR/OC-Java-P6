package com.lpogifr.paymybuddy.controller;

import static com.lpogifr.paymybuddy.utils.AppUtils.asJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lpogifr.paymybuddy.assembler.AccountAssembler;
import com.lpogifr.paymybuddy.model.AccountModel;
import com.lpogifr.paymybuddy.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AccountService service;

  @MockBean
  private AccountAssembler assembler;

  @Test
  void itShouldFindAllAccount() throws Exception {
    mockMvc.perform(get("/account")).andDo(print()).andExpect(status().isOk());
    verify(service).findAll();
  }

  @Test
  void itShouldSave() throws Exception {
    AccountModel testAccount = AccountModel.builder().id(1L).balance(100D).build();
    mockMvc
      .perform(post("/account").content(asJson(testAccount)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isCreated());
    //.andExpect(jsonPath("$.id").value(equals(1L)));
    verify(service).save(any());
  }

  @Test
  void itShouldUpdate() throws Exception {
    AccountModel testAccount = AccountModel.builder().id(1L).balance(100D).build();
    AccountModel updatedAccount = AccountModel.builder().id(1L).balance(105D).build();
    given(service.update(anyLong(), any())).willReturn(updatedAccount);
    mockMvc
      .perform(
        put("/account/1L")
          .content(asJson(updatedAccount))
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().json(asJson(updatedAccount)));
    verify(service).update(anyLong(), updatedAccount);
  }

  @Test
  void itShouldDelete() throws Exception {
    mockMvc.perform(delete("/account/1L")).andDo(print()).andExpect(status().isNoContent());
    verify(service).deleteById(1L);
  }
}
