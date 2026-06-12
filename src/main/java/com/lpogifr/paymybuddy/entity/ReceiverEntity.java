package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "receiver")
public class ReceiverEntity {

  @ManyToOne
  @MapsId("senderId")
  @JoinColumn(name = "sender_id")
  private SenderEntity sender;

  @ManyToOne
  @MapsId("receiverId")
  @JoinColumn(name = "receiver_id")
  private SenderEntity receiver;

  @EmbeddedId
  private ReceiverPrimaryKey id;
}
