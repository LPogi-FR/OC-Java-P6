package com.lpogifr.paymybuddy.front.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {

  private String email;
  private String password;
  private String name;
  private String bic;
  private String iban;
}
