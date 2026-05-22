package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class AccountEntity {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "bcSeqGen", sequenceName = "bc_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "balance")
  private Double balance;

  @OneToOne(mappedBy = "account")
  private SenderEntity senders;
}
