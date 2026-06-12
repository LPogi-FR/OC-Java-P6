package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.front.form.NewreceiverForm;
import com.lpogifr.paymybuddy.front.form.RegisterForm;
import com.lpogifr.paymybuddy.front.form.TransactionForm;
import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.service.SendersService;
import com.lpogifr.paymybuddy.service.TransactionsService;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NavController {

  private final SendersService service;
  private final TransactionsService transactionsService;
  private SenderModel senderModel;

  @GetMapping("/index")
  public String index(Model model, HttpSession session) {
    session.setAttribute("Sender", senderModel);
    model.addAttribute("sender", senderModel);

    return "index";
  }

  @GetMapping("/auth")
  public String auth(Model model) {
    return "auth";
  }

  @GetMapping("/transfert")
  public String transfert(Model model, Principal principal) {
    if (senderModel == null) {
      senderModel = service.findByEmail(principal.getName());
    }
    NewreceiverForm receiverForm = new NewreceiverForm();
    model.addAttribute("receiverForm", receiverForm);
    List<SenderModel> otherSenders = service.findOtherUSers(senderModel.getId());
    model.addAttribute("otherSenders", otherSenders);
    return "menu/transfert";
  }

  @GetMapping({ "/home", "/" })
  public String home(Model model, Principal principal) {
    senderModel = service.findByEmail(principal.getName());
    TransactionForm transactionForm = new TransactionForm();
    model.addAttribute("sender", senderModel);
    model.addAttribute("transactionForm", transactionForm);
    model.addAttribute("account", senderModel.getAccount());
    model.addAttribute("transactionList", transactionsService.findBySenderId(senderModel.getId()));
    return "menu/home";
  }

  @GetMapping("/contact")
  public String contact(Model model) {
    return "menu/contact";
  }

  @GetMapping("/profile")
  public String profile(Model model) {
    model.addAttribute("sender", senderModel);

    return "menu/profile";
  }

  @GetMapping("/logout")
  public String logout(Model model) {
    return "/logout";
  }

  @GetMapping("/login")
  public String login(Model model) {
    return "login";
  }

  @GetMapping("/register")
  public String register(Model model) {
    RegisterForm registerForm = new RegisterForm();
    model.addAttribute("registerForm", registerForm);
    return "register";
  }
}
