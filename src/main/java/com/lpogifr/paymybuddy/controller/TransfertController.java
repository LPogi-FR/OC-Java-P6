package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.assembler.UserAssembler;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.front.form.NewFriendForm;
import com.lpogifr.paymybuddy.front.form.RegisterForm;
import com.lpogifr.paymybuddy.front.form.TransactionForm;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.BankAccountService;
import com.lpogifr.paymybuddy.service.TransactionsService;
import com.lpogifr.paymybuddy.service.UsersService;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
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
  private final BankAccountService bankAccountService;
  private final UsersService usersService;
  private final UserAssembler assembler;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String createTransfert(
    Model model,
    @ModelAttribute TransactionForm transactionForm,
    HttpSession session,
    @AuthenticationPrincipal UserDetails userDetails
  ) {
    UserModel userModel = assembler.fromEntityToModel(((UserEntity) userDetails));
    final var moneyToRecieve = bankAccountService.sendMoney(userModel.getBankAccount(), transactionForm.getAmount());
    bankAccountService.receivceMoney(
      usersService.findByName(transactionForm.getFriend1()).getBankAccount(),
      moneyToRecieve
    );
    //UserModel userModel = usersService.findById(transactionForm.getUserId());
    UserModel friendModel = usersService.findByName(transactionForm.getFriend1());
    final var response = TransactionsModel
      .builder()
      .user(userModel)
      .friend(friendModel)
      .execTime(LocalDateTime.now())
      .amount(transactionForm.getAmount())
      .description(transactionForm.getDescription())
      .build();
    transactionsService.save(response);
    return "redirect:/index";
  }

  @RequestMapping(value = "/newFriend", method = RequestMethod.POST)
  public String addFriend(Model model, @ModelAttribute NewFriendForm friendForm, HttpSession session) {
    UserModel userModel = (UserModel) session.getAttribute("userModel");

    usersService.addFriend(1L, usersService.findByEmail(friendForm.getEmail()).getId());
    return "redirect:/index";
  }

  @RequestMapping(value = "/register/createAccount", method = RequestMethod.POST)
  public String register(Model model, @ModelAttribute RegisterForm registerForm) {
    return "redirect:/index";
  }
}
