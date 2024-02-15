package com.lpogifr.paymybuddy.dto;

import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.model.FriendModel;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

  //private long id;

  private String email;
  private String password;

  private BankAccountModel bankAccount;
  //private List<FriendModel> friendList;
}
