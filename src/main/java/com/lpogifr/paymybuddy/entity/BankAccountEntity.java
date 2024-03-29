package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "bank_account")
public class BankAccountEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "bic")
  private String bic;

  @Column(name = "balance")
  private Double balance;

  @Column(name = "iban")
  private String iban;

  @OneToOne(mappedBy = "bankAccount")
  private UserEntity users;
}
