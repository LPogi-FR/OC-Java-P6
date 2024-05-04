package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.front.form.TransactionForm;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.UsersService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

  private final UsersService service;
  private UserModel userModel;

  @PostConstruct
  public void init() {
    userModel = service.findById(1L);
  }

  @GetMapping("/index")
  public String index(Model model, HttpSession session) {
    session.setAttribute("User", userModel);
    return "index";
  }

  @GetMapping("/auth")
  public String auth(Model model) {
    return "auth";
  }

  @GetMapping("/transfert")
  public String transfert(Model model) {
    TransactionForm transactionForm = new TransactionForm();
    model.addAttribute("transactionForm", transactionForm);
    return "menu/transfert";
  }

  @GetMapping("/home")
  public String home(Model model) {
    return "menu/home";
  }

  @GetMapping("/contact")
  public String contact(Model model) {
    return "menu/contact";
  }

  @GetMapping("/profile")
  public String profile(Model model) {
    return "menu/profile";
  }

  @GetMapping("/logoff")
  public String logoff(Model model) {
    return "menu/logoff";
  }
}
