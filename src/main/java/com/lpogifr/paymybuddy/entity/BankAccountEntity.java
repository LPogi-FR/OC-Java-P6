package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_account")
public class BankAccountEntity {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "bcSeqGen", sequenceName = "bc_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
