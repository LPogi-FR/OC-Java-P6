package com.lpogifr.paymybuddy.assembler;

import static org.junit.jupiter.api.Assertions.*;

import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankAccountAssemblerTest {

  @InjectMocks
  private BankAccountAssembler assembler;

  private final BankAccountModel model = BankAccountModel
    .builder()
    .id(1L)
    .bic("AZERT")
    .iban("QSDFG")
    .balance(200D)
    .build();

  private final BankAccountEntity entity = BankAccountEntity
    .builder()
    .id(1L)
    .bic("AZERT")
    .iban("QSDFG")
    .balance(200D)
    .build();

  @Test
  void itShouldFromModelToEntity() {
    final var bankAccountEntity = assertDoesNotThrow(() -> assembler.fromModelToEntity(model));
    assertEquals(model.getId(), bankAccountEntity.getId());
    assertEquals(model.getIban(), bankAccountEntity.getIban());
    assertEquals(model.getBic(), bankAccountEntity.getBic());
    assertEquals(model.getBalance(), bankAccountEntity.getBalance());

    final var entityNull = assertDoesNotThrow(() -> assembler.fromModelToEntity(null));
    assertNull(entityNull);
  }

  @Test
  void itShouldFromEntityToModel() {
    final var bankAccountModel = assertDoesNotThrow(() -> assembler.fromEntityToModel(entity));
    assertEquals(entity.getId(), bankAccountModel.getId());
    assertEquals(entity.getIban(), bankAccountModel.getIban());
    assertEquals(entity.getBic(), bankAccountModel.getBic());
    assertEquals(entity.getBalance(), bankAccountModel.getBalance());

    final var modelNull = assertDoesNotThrow(() -> assembler.fromEntityToModel(null));
    assertNull(modelNull);
  }

  @Test
  void itShouldFromModelListToEntityList() {
    final var entityList = assertDoesNotThrow(() -> assembler.fromModelListToEntityList(List.of(model, model)));
    assertEquals(2, entityList.size());

    List entityNull = assertDoesNotThrow(() -> assembler.fromModelListToEntityList(null));
    assertNull(entityNull);
    entityNull = assertDoesNotThrow(() -> assembler.fromModelListToEntityList(Collections.EMPTY_LIST));
    assertNull(entityNull);
  }

  @Test
  void itShouldFromEntityListToModelList() {
    final var modelList = assertDoesNotThrow(() -> assembler.fromEntityListToModelList(List.of(entity, entity)));
    assertEquals(2, modelList.size());

    List modelNull = assertDoesNotThrow(() -> assembler.fromEntityListToModelList(null));
    assertNull(modelNull);
    modelNull = assertDoesNotThrow(() -> assembler.fromEntityListToModelList(Collections.EMPTY_LIST));
    assertNull(modelNull);
  }
}
