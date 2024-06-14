package com.lpogifr.paymybuddy.model;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsModel {

  private Long id;

  private UserModel user;

  private UserModel friend;

  private double amount;

  private LocalDateTime execTime;
}
