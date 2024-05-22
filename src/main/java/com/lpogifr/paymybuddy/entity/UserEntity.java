package com.lpogifr.paymybuddy.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

  @Id
  @Column(name = "id", insertable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @OneToOne
  @JoinColumn(name = "bank_account_id", referencedColumnName = "id")
  private BankAccountEntity bankAccount;

  @OneToMany(mappedBy = "friend", fetch = FetchType.LAZY)
  private List<FriendEntity> friendList;

  @Column(name = "name")
  private String name;

  private Boolean isActiveAccount = true;
}
