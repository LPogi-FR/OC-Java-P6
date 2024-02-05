package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @OneToOne
  @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
  private BankAccountEntity bankAccount;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<FriendEntity> friendList;
}
