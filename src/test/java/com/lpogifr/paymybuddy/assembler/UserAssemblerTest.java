package com.lpogifr.paymybuddy.assembler;

import static org.junit.jupiter.api.Assertions.*;

import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.BankAccountModel;
import com.lpogifr.paymybuddy.model.UserModel;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
class UserAssemblerTest {

  @InjectMocks
  private UserAssembler assembler;

  private final UserModel model = UserModel
    .builder()
    .id(1L)
    .password("password")
    .email("email")
    .bankAccount(BankAccountModel.builder().build())
    .friendList(Collections.emptyList())
    .build();

  private final UserEntity entity = UserEntity
    .builder()
    .id(1L)
    .password("password")
    .email("email")
    .bankAccount(BankAccountEntity.builder().build())
    .friendList(Collections.emptyList())
    .build();

  @Test
  void itShouldFromModelToEntity() {
    final var userEntity = assertDoesNotThrow(() -> assembler.fromModelToEntity(model));
    assertEquals(model.getId(), userEntity.getId());
    assertEquals(model.getPassword(), userEntity.getPassword());
    assertEquals(model.getEmail(), userEntity.getEmail());
    //assertEquals(model.getBankAccount(), userEntity.getBankAccount());
    //assertEquals(model.getFriendList(), userEntity.getFriendList());

    final var entityNull = assertDoesNotThrow(() -> assembler.fromModelToEntity(null));
    assertNull(entityNull);
  }

  @Test
  void itShouldFromEntityToModel() {
    final var userModel = assertDoesNotThrow(() -> assembler.fromEntityToModel(entity));
    assertEquals(entity.getId(), userModel.getId());
    assertEquals(entity.getPassword(), userModel.getPassword());
    assertEquals(entity.getEmail(), userModel.getEmail());
    //assertEquals(entity.getBankAccount(), userModel.getBankAccount());
    //assertEquals(entity.getFriendList(), userModel.getFriendList());

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
