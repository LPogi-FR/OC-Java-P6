package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.TransactionsEntity;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@AllArgsConstructor
public class TransactionsAssembler implements IAssembler<TransactionsEntity, TransactionsModel> {

  private UserAssembler userAssembler;

  @Override
  public TransactionsEntity fromModelToEntity(TransactionsModel model) {
    if (model == null) {
      return null;
    }
    return TransactionsEntity
      .builder()
      .id(model.getId())
      .user((userAssembler.fromModelToEntity(model.getUser())))
      .friend((userAssembler.fromModelToEntity(model.getFriend())))
      .amount(model.getAmount())
      .execTime(model.getExecTime())
      .description(model.getDescription())
      .build();
  }

  @Override
  public TransactionsModel fromEntityToModel(TransactionsEntity entity) {
    if (entity == null) {
      return null;
    }
    return TransactionsModel
      .builder()
      .id(entity.getId())
      .user(userAssembler.fromEntityToModel(entity.getUser()))
      .friend(userAssembler.fromEntityToModel(entity.getFriend()))
      .amount(entity.getAmount())
      .execTime(entity.getExecTime())
      .description(entity.getDescription())
      .build();
  }

  @Override
  public List<TransactionsEntity> fromModelListToEntityList(List<TransactionsModel> modelList) {
    if (CollectionUtils.isEmpty(modelList)) {
      return null;
    }
    return modelList.stream().map(this::fromModelToEntity).toList();
  }

  @Override
  public List<TransactionsModel> fromEntityListToModelList(List<TransactionsEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return null;
    }
    return entityList.stream().map(this::fromEntityToModel).toList();
  }
}
