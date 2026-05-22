package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.ReceiverEntity;
import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.model.SenderModel;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ReceiverAssembler implements IAssembler<ReceiverEntity, SenderModel> {

  @Override
  public ReceiverEntity fromModelToEntity(SenderModel model) {
    if (model == null) {
      return null;
    }
    var senderEntity = SenderEntity.builder().id(model.getId()).build();
    return ReceiverEntity.builder().receiver(senderEntity).build();
  }

  @Override
  public SenderModel fromEntityToModel(ReceiverEntity entity) {
    if (entity == null) {
      return null;
    }
    return SenderModel.builder().id(entity.getSender().getId()).name(entity.getSender().getName()).build();
  }

  @Override
  public List<ReceiverEntity> fromModelListToEntityList(List<SenderModel> modelList) {
    if (CollectionUtils.isEmpty(modelList)) {
      return null;
    }
    return modelList.stream().map(this::fromModelToEntity).toList();
  }

  @Override
  public List<SenderModel> fromEntityListToModelList(List<ReceiverEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return null;
    }
    return entityList.stream().map(this::fromEntityToModel).toList();
  }
}
