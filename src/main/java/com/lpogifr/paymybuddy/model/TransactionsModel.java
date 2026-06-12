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

  private SenderModel sender;

  private SenderModel receiver;

  private double amount;

  private LocalDateTime execTime;
  private String description;
}
