package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class TransactionsEntity {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name = "trSeqGen", sequenceName = "tr_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "receiver_id", referencedColumnName = "id")
  private UserEntity receiver;

  private double amount;

  private LocalDateTime execTime;

  private String description;
}
