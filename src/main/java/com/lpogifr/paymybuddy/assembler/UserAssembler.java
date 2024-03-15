package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
public class UserAssembler implements IAssembler<UserEntity, UserModel> {

  private BankAccountAssembler bankAccountAssembler;
  private FriendAssembler friendAssembler;

  @Override
  public UserEntity fromModelToEntity(UserModel model) {
    if (model == null) {
      return null;
    }
    return UserEntity
      .builder()
      .id(model.getId())
      .email(model.getEmail())
      .password(model.getPassword())
      .friendList(friendAssembler.fromModelListToEntityList(model.getFriendList()))
      .bankAccount(bankAccountAssembler.fromModelToEntity(model.getBankAccount()))
      .build();
  }

  @Override
  public UserModel fromEntityToModel(UserEntity entity) {
    if (entity == null) {
      return null;
    }
    return UserModel
      .builder()
      .id(entity.getId())
      .bankAccount(bankAccountAssembler.fromEntityToModel(entity.getBankAccount()))
      .friendList(friendAssembler.fromEntityListToModelList(entity.getFriendList()))
      .email(entity.getEmail())
      .password(entity.getPassword())
      .build();
  }

  @Override
  public List<UserEntity> fromModelListToEntityList(List<UserModel> modelList) {
    if (CollectionUtils.isEmpty(modelList)) {
      return null;
    }
    return modelList.stream().map(this::fromModelToEntity).toList();
  }

  @Override
  public List<UserModel> fromEntityListToModelList(List<UserEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return null;
    }
    return entityList.stream().map(this::fromEntityToModel).toList();
  }
}
