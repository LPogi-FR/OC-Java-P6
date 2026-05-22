package com.lpogifr.paymybuddy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestModel {

  private Long senderId;
  private Long receiverId;
  private double amount;
}
