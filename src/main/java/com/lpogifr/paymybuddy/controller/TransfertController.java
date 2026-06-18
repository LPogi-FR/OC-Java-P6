package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.SenderAssembler;
import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.front.form.NewreceiverForm;
import com.lpogifr.paymybuddy.front.form.RegisterForm;
import com.lpogifr.paymybuddy.front.form.TransactionForm;
import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.service.AccountService;
import com.lpogifr.paymybuddy.service.SendersService;
import com.lpogifr.paymybuddy.service.TransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

  @Transactional
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String createTransfert(
    @ModelAttribute TransactionForm transactionForm,
    @AuthenticationPrincipal UserDetails userDetails,
    Model model
  ) {
    SenderModel senderModel = assembler.fromEntityToModel(((SenderEntity) userDetails));
    SenderModel receiverModel = sendersService.findByEmail(transactionForm.getReceiverEmail());
    transactionForm.setSenderId(senderModel.getId());
    transactionForm.setReceiverId(receiverModel.getId());
    transactionsService.createNewTransaction(transactionForm);

    return "redirect:/home";
  }

  @RequestMapping(value = "/newreceiver", method = RequestMethod.POST)
  public String addreceiver(
    @ModelAttribute NewreceiverForm receiverForm,
    @AuthenticationPrincipal UserDetails userDetails
  ) {
    SenderModel senderModel = assembler.fromEntityToModel(((SenderEntity) userDetails));
    senderModel =
      sendersService.addReceiver(senderModel.getId(), sendersService.findByEmail(receiverForm.getEmail()).getId());
    ((SenderEntity) userDetails).setReceiverList(assembler.fromModelToEntity(senderModel).getReceiverList());
    sendersService.update(senderModel.getId(), senderModel);
    return "redirect:/home";
  }

  @RequestMapping(value = "/registerNewAccount", method = RequestMethod.POST)
  public String register(@ModelAttribute RegisterForm registerForm) {
    sendersService.createSender(registerForm, accountService.createNewAccount());
    return "redirect:/login";
  }
}
