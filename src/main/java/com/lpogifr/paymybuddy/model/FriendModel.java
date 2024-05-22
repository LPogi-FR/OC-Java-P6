package com.lpogifr.paymybuddy.model;

import com.lpogifr.paymybuddy.entity.FriendPrimaryKey;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendModel {

  private UserModel friend;

  private UserModel user;

  private FriendPrimaryKey id;

  private String name;
}
