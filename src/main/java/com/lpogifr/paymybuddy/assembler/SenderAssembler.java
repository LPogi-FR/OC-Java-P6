package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.model.SenderModel;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
public class SenderAssembler implements IAssembler<SenderEntity, SenderModel> {

  private AccountAssembler accountAssembler;
  private ReceiverAssembler receiverAssembler;

  @Override
  public SenderEntity fromModelToEntity(SenderModel model) {
    if (model == null) {
      return null;
    }
    return SenderEntity
      .builder()
      .id(model.getId())
      .email(model.getEmail())
      .password(model.getPassword())
      .account(accountAssembler.fromModelToEntity(model.getAccount()))
      .receiverList(receiverAssembler.fromModelListToEntityList(model.getReceiverList()))
      .name(model.getName())
      .build();
  }

  @Override
  public SenderModel fromEntityToModel(SenderEntity entity) {
    if (entity == null) {
      return null;
    }
    return SenderModel
      .builder()
      .id(entity.getId())
      .account(accountAssembler.fromEntityToModel(entity.getAccount()))
      .receiverList(receiverAssembler.fromEntityListToModelList(entity.getReceiverList()))
      .email(entity.getEmail())
      .password(entity.getPassword())
      .name(entity.getName())
      .build();
  }

  @Override
  public List<SenderEntity> fromModelListToEntityList(List<SenderModel> modelList) {
    if (CollectionUtils.isEmpty(modelList)) {
      return null;
    }
    return modelList.stream().map(this::fromModelToEntity).toList();
  }

  @Override
  public List<SenderModel> fromEntityListToModelList(List<SenderEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return null;
    }
    return entityList.stream().map(this::fromEntityToModel).toList();
  }
}
