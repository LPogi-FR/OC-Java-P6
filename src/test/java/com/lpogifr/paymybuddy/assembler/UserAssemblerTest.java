package com.lpogifr.paymybuddy.assembler;

import static org.junit.jupiter.api.Assertions.*;

import com.lpogifr.paymybuddy.entity.AccountEntity;
import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.model.AccountModel;
import com.lpogifr.paymybuddy.model.SenderModel;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SenderAssemblerTest {

  @InjectMocks
  private SenderAssembler assembler;

  private final SenderModel model = SenderModel
    .builder()
    .id(1L)
    .password("password")
    .email("email")
    .account(AccountModel.builder().build())
    .receiverList(Collections.emptyList())
    .build();

  private final SenderEntity entity = SenderEntity
    .builder()
    .id(1L)
    .password("password")
    .email("email")
    .account(AccountEntity.builder().build())
    .receiverList(Collections.emptyList())
    .build();

  @Test
  void itShouldFromModelToEntity() {
    final var senderEntity = assertDoesNotThrow(() -> assembler.fromModelToEntity(model));
    assertEquals(model.getId(), senderEntity.getId());
    assertEquals(model.getPassword(), senderEntity.getPassword());
    assertEquals(model.getEmail(), senderEntity.getEmail());
    final var entityNull = assertDoesNotThrow(() -> assembler.fromModelToEntity(null));
    assertNull(entityNull);
  }

  @Test
  void itShouldFromEntityToModel() {
    final var senderModel = assertDoesNotThrow(() -> assembler.fromEntityToModel(entity));
    assertEquals(entity.getId(), senderModel.getId());
    assertEquals(entity.getPassword(), senderModel.getPassword());
    assertEquals(entity.getEmail(), senderModel.getEmail());
    final var entityNull = assertDoesNotThrow(() -> assembler.fromModelToEntity(null));
    assertNull(entityNull);
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
