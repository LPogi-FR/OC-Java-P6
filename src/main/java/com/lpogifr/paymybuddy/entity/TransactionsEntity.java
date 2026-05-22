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
  @JoinColumn(name = "sender_id", referencedColumnName = "id")
  private SenderEntity sender;

  @ManyToOne
  @JoinColumn(name = "receiver_id", referencedColumnName = "id")
  private SenderEntity receiver;

  private double amount;

  private LocalDateTime execTime;

  private String description;
}
