package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class BankAccountAssembler implements IAssembler<BankAccountEntity, BankAccountModel> {

  @Override
  public BankAccountEntity fromModelToEntity(BankAccountModel model) {
    if (model == null) {
      return null;
    }
    return BankAccountEntity
      .builder()
      .id(model.getId())
      .bic(model.getBic())
      .balance(model.getBalance())
      .iban(model.getIban())
      .build();
  }

  @Override
  public BankAccountModel fromEntityToModel(BankAccountEntity entity) {
    if (entity == null) {
      return null;
    }
    return BankAccountModel
      .builder()
      .id(entity.getId())
      .bic(entity.getBic())
      .balance(entity.getBalance())
      .iban(entity.getIban())
      .build();
  }

  @Override
  public List<BankAccountEntity> fromModelListToEntityList(List<BankAccountModel> modelList) {
    if (CollectionUtils.isEmpty(modelList)) {
      return null;
    }
    return modelList.stream().map(this::fromModelToEntity).toList();
  }

  @Override
  public List<BankAccountModel> fromEntityListToModelList(List<BankAccountEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return null;
    }
    return entityList.stream().map(this::fromEntityToModel).toList();
  }
}
