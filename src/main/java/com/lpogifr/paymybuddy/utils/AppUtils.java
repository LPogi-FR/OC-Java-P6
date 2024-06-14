package com.lpogifr.paymybuddy.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUtils {

  public static Boolean isBalancePositive(double balance, double amountToSend) {
    return balance - amountToSend >= 0;
  }

  public static Boolean isSentAmountPositiveAndNotNull(double amountToSend) {
    return amountToSend >= 0 && amountToSend != 0;
  }
}
