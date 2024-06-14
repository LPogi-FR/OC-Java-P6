package com.lpogifr.paymybuddy.front.form;

import com.lpogifr.paymybuddy.model.UserModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionForm {

  private String description;
  private double amount;
  private Long userId;
  private Long friendId;
  private String friend1;
}
