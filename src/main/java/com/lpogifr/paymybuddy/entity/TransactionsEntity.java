package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;

  @OneToOne
  @JoinColumn(name = "friend_id", referencedColumnName = "id")
  private UserEntity friend;

  private double amount;

  private LocalDateTime execTime;
}
