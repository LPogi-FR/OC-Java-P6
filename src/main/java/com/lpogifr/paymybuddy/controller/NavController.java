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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class NavController {

  private final SendersService service;
  private final TransactionsService transactionsService;
  private SenderModel senderModel;

  @GetMapping("/index")
  public String index(Model model, HttpSession session) {
    session.setAttribute("Sender", senderModel);
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
    TransactionForm transactionForm = new TransactionForm();
    NewreceiverForm receiverForm = new NewreceiverForm();
    List<SenderModel> otherSenders = service.findOtherUSers(senderModel.getId());
    model.addAttribute("sender", senderModel);
    model.addAttribute("transactionForm", transactionForm);
    model.addAttribute("receiverForm", receiverForm);
    model.addAttribute("otherSenders", otherSenders);
    return "menu/transfert";
  }

  @GetMapping({ "/home", "/" })
  public String home(Model model, Principal principal) {
    senderModel = service.findByEmail(principal.getName());
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

  @GetMapping("/logoff")
  public String logoff(Model model) {
    return "menu/logoff";
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

  @RequestMapping(value = "/registerNewAccount", method = RequestMethod.POST)
  public String registerNewAccount(Model model, @ModelAttribute RegisterForm registerForm) {
    return "redirect:/index";
  }
}
