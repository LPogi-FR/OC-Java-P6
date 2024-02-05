package com.lpogifr.paymybuddy.model;

import com.lpogifr.paymybuddy.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendModel {

  private long friendId;

  private UserEntity user;
}
