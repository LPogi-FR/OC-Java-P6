package com.lpogifr.paymybuddy.front.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionForm {

  private String description;
  private double amount;
  private Long userId;
  private Long friendId;
}
