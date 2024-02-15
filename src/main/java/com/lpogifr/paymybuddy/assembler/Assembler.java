package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.entity.FriendEntity;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.model.FriendModel;
import com.lpogifr.paymybuddy.model.UserModel;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class Assembler {

  public List<UserModel> userEntityToModel(List<UserEntity> userEntityList) {
    if (CollectionUtils.isEmpty(userEntityList)) {
      return null;
    }
    return userEntityList.stream().map(this::userEntityToModel).toList();
  }

  public UserModel userEntityToModel(UserEntity userEntity) {
    if (userEntity == null) {
      return null;
    }
    return UserModel
      .builder()
      .id(userEntity.getId())
      .email(userEntity.getEmail())
      .bankAccount(bankAccountEntityToModel(userEntity.getBankAccount()))
      //.friendList(friendEntityToModel(userEntity.getFriendList()))
      .build();
  }

  /*public List<FriendModel> friendEntityToModel(List<FriendEntity> friendEntityList) {
    return friendEntityList.stream().map(this::friendEntityToModel).toList();
  }

  /*private FriendModel friendEntityToModel(FriendEntity friendEntity) {
    return FriendModel.builder().friend(friendEntity.getFriend()).user(friendEntity.getUser()).build();
  }*/

  public List<BankAccountModel> bankAccountEntityToModel(List<BankAccountEntity> bankAccountEntityList) {
    return bankAccountEntityList.stream().map(this::bankAccountEntityToModel).toList();
  }

  private BankAccountModel bankAccountEntityToModel(BankAccountEntity bankAccountEntity) {
    return BankAccountModel
      .builder()
      .bic(bankAccountEntity.getBic())
      .iban(bankAccountEntity.getIban())
      .balance(bankAccountEntity.getBalance())
      .id(bankAccountEntity.getId())
      .build();
  }
}
