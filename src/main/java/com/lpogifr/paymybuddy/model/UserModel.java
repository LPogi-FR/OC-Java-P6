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

  private BankAccountModel bankAccount;

  private List<UserModel> friendList;

  private String name;
}
