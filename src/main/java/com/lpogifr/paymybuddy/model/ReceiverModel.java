package com.lpogifr.paymybuddy.model;

import com.lpogifr.paymybuddy.entity.ReceiverPrimaryKey;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverModel {

  private UserModel receiver;

  private UserModel user;

  private ReceiverPrimaryKey id;

  private String name;
}
