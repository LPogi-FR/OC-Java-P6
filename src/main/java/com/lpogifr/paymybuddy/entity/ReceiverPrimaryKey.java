package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverPrimaryKey {

  @Column(name = "sender_id", insertable = false, updatable = false)
  private Long senderId;

  @Column(name = "receiver_id", insertable = false, updatable = false)
  private Long receiverId;
}
