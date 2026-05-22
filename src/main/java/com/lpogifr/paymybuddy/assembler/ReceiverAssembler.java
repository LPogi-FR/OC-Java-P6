package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.ReceiverEntity;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ReceiverAssembler implements IAssembler<ReceiverEntity, UserModel> {

  @Override
  public ReceiverEntity fromModelToEntity(UserModel model) {
    if (model == null) {
      return null;
    }
    var userEntity = UserEntity.builder().id(model.getId()).build();
    return ReceiverEntity.builder().receiver(userEntity).build();
  }

  @Override
  public UserModel fromEntityToModel(ReceiverEntity entity) {
    if (entity == null) {
      return null;
    }
    return UserModel.builder().id(entity.getUser().getId()).name(entity.getUser().getName()).build();
  }

  @Override
  public List<ReceiverEntity> fromModelListToEntityList(List<UserModel> modelList) {
    if (CollectionUtils.isEmpty(modelList)) {
      return null;
    }
    return modelList.stream().map(this::fromModelToEntity).toList();
  }

  @Override
  public List<UserModel> fromEntityListToModelList(List<ReceiverEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return null;
    }
    return entityList.stream().map(this::fromEntityToModel).toList();
  }
}
