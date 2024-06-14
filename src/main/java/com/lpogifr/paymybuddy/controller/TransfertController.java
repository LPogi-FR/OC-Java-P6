package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.front.form.TransactionForm;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.BankAccountService;
import com.lpogifr.paymybuddy.service.TransactionsService;
import com.lpogifr.paymybuddy.service.UsersService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
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
  private final BankAccountService bankAccountService;
  private final UsersService usersService;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String createTransfert(Model model, @ModelAttribute TransactionForm transactionForm, HttpSession session) {
    UserModel userModel = (UserModel) session.getAttribute("userModel");
    if (userModel == null) {
      transactionForm.setUserId(1L);
    }
    final var moneyToRecieve = bankAccountService.sendMoney(
      usersService.findById(transactionForm.getUserId()).getBankAccount(),
      transactionForm.getAmount()
    );
    bankAccountService.receivceMoney(
      usersService.findById(transactionForm.getFriendId()).getBankAccount(),
      moneyToRecieve
    );
    //UserModel userModel = usersService.findById(transactionForm.getUserId());
    UserModel friendModel = usersService.findById(transactionForm.getFriendId());
    final var response = TransactionsModel
      .builder()
      .user(userModel)
      .friend(friendModel)
      .execTime(LocalDateTime.now())
      .amount(transactionForm.getAmount())
      .build();
    transactionsService.save(response);
    return "redirect:/index";
  }
}
