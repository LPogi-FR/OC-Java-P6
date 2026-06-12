package com.lpogifr.paymybuddy.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SenderModel {

  private Long id;

  private String email;

  private String password;

  private AccountModel account;

  private List<SenderModel> receiverList;

  private List<SenderModel> senderList;

  private String name;
}
