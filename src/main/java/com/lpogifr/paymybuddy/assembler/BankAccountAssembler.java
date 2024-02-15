package com.lpogifr.paymybuddy.assembler;

import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BankAccountAssembler implements IAssembler<BankAccountEntity, BankAccountModel> {

  @Override
  public BankAccountEntity fromModelToEntity(BankAccountModel model) {
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
    return null;
  }

  @Override
  public List<BankAccountModel> fromEntityListToModelList(List<BankAccountEntity> entityList) {
    return null;
  }
}
