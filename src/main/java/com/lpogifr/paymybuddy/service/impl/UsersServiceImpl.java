package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.BankAccountAssembler;
import com.lpogifr.paymybuddy.assembler.UserAssembler;
import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.entity.FriendEntity;
import com.lpogifr.paymybuddy.entity.FriendPrimaryKey;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.repository.BankAccountRepository;
import com.lpogifr.paymybuddy.repository.FriendRepository;
import com.lpogifr.paymybuddy.repository.UsersRepository;
import com.lpogifr.paymybuddy.service.UsersService;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

  private final UsersRepository repository;

  private final BankAccountRepository bankAccountRepository;

  private final FriendRepository friendRepository;

  private final UserAssembler assembler;

  private final BankAccountAssembler bankAccountAssembler;

  @Override
  public List<UserModel> findAll() {
    return this.assembler.fromEntityListToModelList(repository.findAll());
  }

  @Override
  public UserModel findByEmail(String email) {
    UserEntity entity = repository.findByEmail(email);
    return assembler.fromEntityToModel(entity);
  }

  @Override
  public UserModel findByName(String name) {
    UserEntity entity = repository.findByName(name);
    return assembler.fromEntityToModel(entity);
  }

  @Override
  public UserModel findById(Long id) {
    Optional<UserEntity> entityOptional = repository.findById(id);
    return entityOptional.map(assembler::fromEntityToModel).orElse(null);
  }

  @Override
  public UserModel save(UserModel newUser) {
    UserEntity entityToSave = assembler.fromModelToEntity(newUser);
    BankAccountEntity bankAccountEntity = bankAccountRepository.save(createBankAccount(entityToSave));
    entityToSave.setBankAccount(bankAccountEntity);
    UserEntity savedUserEntity = repository.save(entityToSave);
    bankAccountEntity.setUsers(savedUserEntity);
    bankAccountEntity = bankAccountRepository.save(bankAccountEntity);
    UserModel saved = assembler.fromEntityToModel(savedUserEntity);
    saved.setBankAccount(bankAccountAssembler.fromEntityToModel(bankAccountEntity));
    return saved;
  }

  private BankAccountEntity createBankAccount(UserEntity entityToSave) {
    BankAccountEntity bankAccount = new BankAccountEntity();
    bankAccount.setUsers(entityToSave);
    bankAccount.setIban(UUID.randomUUID().toString());
    bankAccount.setBic("CFRREFF");
    double leftLimit = 100D;
    double rightLimit = 1000D;
    bankAccount.setBalance(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
    return bankAccount;
  }

  @Override
  public void delete(String email) {
    repository.deleteByEmail(email);
  }

  public UserModel update(UserModel updatedUser) {
    return assembler.fromEntityToModel(repository.save(assembler.fromModelToEntity(updatedUser)));
  }

  @Override
  public UserModel addFriend(Long id, Long friendId) {
    Optional<UserEntity> response = null;
    UserEntity userEntity = repository.findById(id).orElse(null);
    UserEntity newFriend = repository.findById(friendId).orElse(null);
    if (userEntity != null) {
      final var newFriendEntity = new FriendEntity()
        .builder()
        .id(FriendPrimaryKey.builder().userId(userEntity.getId()).friendId(newFriend.getId()).build())
        .user(userEntity)
        .friend(newFriend)
        .build();
      List<FriendEntity> friendEntityList = userEntity.getFriendList();
      friendRepository.save(newFriendEntity);
      //userEntity.setFriendList(friendEntityList);
      response = repository.findById(id);
    }
    return assembler.fromEntityToModel(response.orElse(null));
  }

  @Override
  public List<UserModel> findOtherUSers(Long userId) {
    return assembler.fromEntityListToModelList(repository.findOtheUser(userId));
  }
}
