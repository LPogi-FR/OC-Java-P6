package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionsEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;

  @OneToOne
  @JoinColumn(name = "friend_id", referencedColumnName = "id")
  private FriendEntity friend;

  private double amount;

  private LocalDateTime executionTime;
}
