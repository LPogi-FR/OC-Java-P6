package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.SenderAssembler;
import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.front.form.NewreceiverForm;
import com.lpogifr.paymybuddy.front.form.TransactionForm;
import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import com.lpogifr.paymybuddy.service.AccountService;
import com.lpogifr.paymybuddy.service.SendersService;
import com.lpogifr.paymybuddy.service.TransactionsService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/transfert")
@AllArgsConstructor
public class TransfertController {

  private final TransactionsService transactionsService;
  private final AccountService accountService;
  private final SendersService sendersService;
  private final SenderAssembler assembler;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String createTransfert(
    Model model,
    @ModelAttribute TransactionForm transactionForm,
    HttpSession session,
    @AuthenticationPrincipal UserDetails userDetails
  ) {
    SenderModel senderModel = assembler.fromEntityToModel(((SenderEntity) userDetails));
    final var moneyToRecieve = accountService.sendMoney(senderModel.getAccount(), transactionForm.getAmount());
    accountService.receivceMoney(
      sendersService.findByName(transactionForm.getReceiverName()).getAccount(),
      moneyToRecieve
    );
    SenderModel receiverModel = sendersService.findByName(transactionForm.getReceiverName());
    final var response = TransactionsModel
      .builder()
      .sender(senderModel)
      .receiver(receiverModel)
      .execTime(LocalDateTime.now())
      .amount(transactionForm.getAmount())
      .description(transactionForm.getDescription())
      .build();
    transactionsService.save(response);
    return "redirect:/index";
  }

  @RequestMapping(value = "/newreceiver", method = RequestMethod.POST)
  public String addreceiver(Model model, @ModelAttribute NewreceiverForm receiverForm, HttpSession session) {
    SenderModel senderModel = (SenderModel) session.getAttribute("senderModel");

    sendersService.addreceiver(1L, sendersService.findByEmail(receiverForm.getEmail()).getId());
    return "redirect:/index";
  }
  /*
  @RequestMapping(value = "/register/createAccount", method = RequestMethod.POST)
  public String register(Model model, @ModelAttribute RegisterForm registerForm) {

    return "redirect:/index";
  }*/
}
