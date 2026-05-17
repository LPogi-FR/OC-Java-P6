package com.lpogifr.paymybuddy.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

  private Long id;

  private String email;

  private String password;

  private AccountModel account;

  private List<UserModel> friendList;

  private String name;
}
