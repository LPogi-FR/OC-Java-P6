package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.FriendEntity;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.FriendModel;
import com.lpogifr.paymybuddy.model.UserModel;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class FriendAssembler implements IAssembler<FriendEntity, UserModel> {

  @Override
  public FriendEntity fromModelToEntity(UserModel model) {
    if (model == null) {
      return null;
    }
    var userEntity = UserEntity.builder().id(model.getId()).build();
    return FriendEntity.builder().friend(userEntity).build();
  }

  @Override
  public UserModel fromEntityToModel(FriendEntity entity) {
    if (entity == null) {
      return null;
    }
    return UserModel.builder().id(entity.getFriend().getId()).build();
  }

  @Override
  public List<FriendEntity> fromModelListToEntityList(List<UserModel> modelList) {
    if (CollectionUtils.isEmpty(modelList)) {
      return null;
    }
    return modelList.stream().map(this::fromModelToEntity).toList();
  }

  @Override
  public List<UserModel> fromEntityListToModelList(List<FriendEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return null;
    }
    return entityList.stream().map(this::fromEntityToModel).toList();
  }
}
