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
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne
  @MapsId("receiverId")
  @JoinColumn(name = "receiver_id")
  private UserEntity receiver;

  @EmbeddedId
  private ReceiverPrimaryKey id;
}
