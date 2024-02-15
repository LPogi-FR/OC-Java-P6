package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FriendPrimaryKey {

  @Column(name = "user_id", insertable = false, updatable = false)
  private long userId;

  @Column(name = "friend_id", insertable = false, updatable = false)
  private long friendId;
}
