package com.lpogifr.paymybuddy.utils;

import static com.lpogifr.paymybuddy.utils.AppUtils.isBalancePositive;
import static com.lpogifr.paymybuddy.utils.AppUtils.isSentAmountPositiveAndNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppUtilsTest {

  private final double positiveValue = 100;
  private final double nullValue = 0;

  @Test
  void isBalancePositiveTest() {
    assertFalse(isBalancePositive(nullValue, positiveValue));
    assertTrue(isBalancePositive(positiveValue, positiveValue));
  }

  @Test
  void isSentAmountPositiveAndNotNullTest() {
    assertTrue(isSentAmountPositiveAndNotNull(positiveValue));
    assertFalse(isSentAmountPositiveAndNotNull(nullValue));
    double negativeValue = -100;
    assertFalse(isSentAmountPositiveAndNotNull(negativeValue));
  }
}
