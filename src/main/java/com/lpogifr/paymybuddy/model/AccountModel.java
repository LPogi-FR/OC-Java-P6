package com.lpogifr.paymybuddy.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

  private Long id;

  private Double balance;
}
