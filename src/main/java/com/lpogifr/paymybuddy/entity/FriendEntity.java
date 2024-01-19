package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "friend")
public class FriendEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;
}
