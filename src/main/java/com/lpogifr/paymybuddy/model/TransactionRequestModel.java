package com.lpogifr.paymybuddy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestModel {

  private Long userId;
  private Long receiverId;
  private double amount;
}
