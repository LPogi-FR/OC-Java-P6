package com.lpogifr.paymybuddy.controller;

import static com.lpogifr.paymybuddy.utils.AppUtils.asJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lpogifr.paymybuddy.assembler.BankAccountAssembler;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BankAccountController.class)
class BankAccountControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BankAccountService service;

  @MockBean
  private BankAccountAssembler assembler;

  /*//@SpyBean
  //private BankAccountController controller;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.openMocks(this);
  }*/

  @Test
  void itShouldFindAllBankAccount() throws Exception {
    mockMvc.perform(get("/bankAccount")).andDo(print()).andExpect(status().isOk());
    verify(service).findAll();
  }

  @Test
  void itShouldSave() throws Exception {
    BankAccountModel testBankAccount = BankAccountModel.builder().iban("iban").id(1L).bic("bic").balance(100D).build();
    mockMvc
      .perform(post("/bankAccount").content(asJson(testBankAccount)).contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isCreated());
    //.andExpect(jsonPath("$.id").value(equals(1L)));
    verify(service).save(any());
  }

  @Test
  void itShouldUpdate() throws Exception {
    BankAccountModel testBankAccount = BankAccountModel.builder().iban("iban").id(1L).bic("bic").balance(100D).build();
    BankAccountModel updatedAccount = BankAccountModel.builder().iban("iban1").id(1L).bic("bic1").balance(105D).build();
    given(service.update(anyLong(), any())).willReturn(updatedAccount);
    mockMvc
      .perform(
        put("/bankAccount/1L")
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
    mockMvc.perform(delete("/banckAccount/1L")).andDo(print()).andExpect(status().isNoContent());
    verify(service).deleteById(1L);
  }
}
