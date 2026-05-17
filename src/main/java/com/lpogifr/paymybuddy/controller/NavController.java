package com.lpogifr.paymybuddy.controller;

import com.lpogifr.paymybuddy.front.form.NewFriendForm;
import com.lpogifr.paymybuddy.front.form.RegisterForm;
import com.lpogifr.paymybuddy.front.form.TransactionForm;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.service.TransactionsService;
import com.lpogifr.paymybuddy.service.UsersService;
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

  private final UsersService service;
  private final TransactionsService transactionsService;
  private UserModel userModel;

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
  public String transfert(Model model, Principal principal) {
    if (userModel == null) {
      userModel = service.findByEmail(principal.getName());
    }
    TransactionForm transactionForm = new TransactionForm();
    NewFriendForm friendForm = new NewFriendForm();
    List<UserModel> otherUsers = service.findOtherUSers(userModel.getId());
    model.addAttribute("user", userModel);
    model.addAttribute("transactionForm", transactionForm);
    model.addAttribute("friendForm", friendForm);
    model.addAttribute("otherUsers", otherUsers);
    return "menu/transfert";
  }

  @GetMapping({ "/home", "/" })
  public String home(Model model, Principal principal) {
    userModel = service.findByEmail(principal.getName());
    model.addAttribute("account", userModel.getAccount());
    model.addAttribute("transactionList", transactionsService.findByUserId(userModel.getId()));
    return "menu/home";
  }

  @GetMapping("/contact")
  public String contact(Model model) {
    return "menu/contact";
  }

  @GetMapping("/profile")
  public String profile(Model model) {
    model.addAttribute("user", userModel);

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
