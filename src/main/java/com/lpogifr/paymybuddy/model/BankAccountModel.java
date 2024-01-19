package com.lpogifr.paymybuddy.model;

import com.lpogifr.paymybuddy.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountModel {

  private Long id;

  private String bic;

  private Double balance;

  private String iban;
  //private UserEntity users;
}
