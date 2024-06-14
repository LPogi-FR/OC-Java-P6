package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FriendPrimaryKey {

  @Column(name = "friend_id")
  private long friendID;

  @Column(name = "user_id")
  private long userID;
}
