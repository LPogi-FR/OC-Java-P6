package com.lpogifr.paymybuddy.assembler;

import static org.junit.jupiter.api.Assertions.*;

import com.lpogifr.paymybuddy.entity.AccountEntity;
import com.lpogifr.paymybuddy.model.AccountModel;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountAssemblerTest {

  @InjectMocks
  private AccountAssembler assembler;

  private final AccountModel model = AccountModel.builder().id(1L).balance(200D).build();

  private final AccountEntity entity = AccountEntity.builder().id(1L).balance(200D).build();

  @Test
  void itShouldFromModelToEntity() {
    final var AccountEntity = assertDoesNotThrow(() -> assembler.fromModelToEntity(model));
    assertEquals(model.getId(), AccountEntity.getId());
    assertEquals(model.getBalance(), AccountEntity.getBalance());

    final var entityNull = assertDoesNotThrow(() -> assembler.fromModelToEntity(null));
    assertNull(entityNull);
  }

  @Test
  void itShouldFromEntityToModel() {
    final var accountModel = assertDoesNotThrow(() -> assembler.fromEntityToModel(entity));
    assertEquals(entity.getId(), accountModel.getId());
    assertEquals(entity.getBalance(), accountModel.getBalance());

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
