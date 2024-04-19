package com.lpogifr.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/index")
  public String index(Model model) {
    return "index";
  }

  @GetMapping("/auth")
  public String auth(Model model) {
    return "auth";
  }

  @GetMapping("/transfert")
  public String transfert(Model model) {
    return "transfert";
  }
}
