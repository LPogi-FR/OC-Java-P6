package com.lpogifr.paymybuddy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PayMyBuddyApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> PayMyBuddyApplication.main(new String[] {}));
  }
}
