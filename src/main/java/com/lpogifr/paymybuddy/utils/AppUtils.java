package com.lpogifr.paymybuddy.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUtils {

  @Getter
  private static final ObjectMapper mapper = new ObjectMapper();

  public static Boolean isBalancePositive(double balance, double amountToSend) {
    return balance - amountToSend >= 0;
  }

  public static Boolean isSentAmountPositiveAndNotNull(double amountToSend) {
    return amountToSend >= 0 && amountToSend != 0;
  }

  public static String asJson(final Object obj) {
    try {
      return mapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
