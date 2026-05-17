package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.AccountEntity;
import com.lpogifr.paymybuddy.model.AccountModel;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class AccountAssembler implements IAssembler<AccountEntity, AccountModel> {

  @Override
  public AccountEntity fromModelToEntity(AccountModel model) {
    if (model == null) {
      return null;
    }
    return AccountEntity.builder().id(model.getId()).balance(model.getBalance()).build();
  }

  @Override
  public AccountModel fromEntityToModel(AccountEntity entity) {
    if (entity == null) {
      return null;
    }
    return AccountModel.builder().id(entity.getId()).balance(entity.getBalance()).build();
  }

  @Override
  public List<AccountEntity> fromModelListToEntityList(List<AccountModel> modelList) {
    if (CollectionUtils.isEmpty(modelList)) {
      return null;
    }
    return modelList.stream().map(this::fromModelToEntity).toList();
  }

  @Override
  public List<AccountModel> fromEntityListToModelList(List<AccountEntity> entityList) {
    if (CollectionUtils.isEmpty(entityList)) {
      return null;
    }
    return entityList.stream().map(this::fromEntityToModel).toList();
  }
}
