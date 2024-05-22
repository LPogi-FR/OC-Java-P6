package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "friend")
public class FriendEntity {

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne
  @MapsId("friendId")
  @JoinColumn(name = "friend_id")
  private UserEntity friend;

  private String name;

  @EmbeddedId
  private FriendPrimaryKey id;
}
