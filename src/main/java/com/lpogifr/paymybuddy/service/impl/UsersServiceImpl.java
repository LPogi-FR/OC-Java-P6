package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.AccountAssembler;
import com.lpogifr.paymybuddy.assembler.UserAssembler;
import com.lpogifr.paymybuddy.entity.AccountEntity;
import com.lpogifr.paymybuddy.entity.FriendEntity;
import com.lpogifr.paymybuddy.entity.FriendPrimaryKey;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.repository.AccountRepository;
import com.lpogifr.paymybuddy.repository.FriendRepository;
import com.lpogifr.paymybuddy.repository.UsersRepository;
import com.lpogifr.paymybuddy.service.UsersService;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

  private final UsersRepository repository;

  private final AccountRepository accountRepository;

  private final FriendRepository friendRepository;

  private final UserAssembler assembler;

  private final AccountAssembler accountAssembler;

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
    AccountEntity accountEntity = accountRepository.save(createAccount(entityToSave));
    entityToSave.setAccount(accountEntity);
    UserEntity savedUserEntity = repository.save(entityToSave);
    accountEntity.setUsers(savedUserEntity);
    accountEntity = accountRepository.save(accountEntity);
    UserModel saved = assembler.fromEntityToModel(savedUserEntity);
    saved.setAccount(accountAssembler.fromEntityToModel(accountEntity));
    return saved;
  }

  private AccountEntity createAccount(UserEntity entityToSave) {
    AccountEntity account = new AccountEntity();
    account.setUsers(entityToSave);
    double leftLimit = 100D;
    double rightLimit = 1000D;
    account.setBalance(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
    return account;
  }

  @Override
  public void delete(String email) {
    repository.deleteByEmail(email);
  }

  public UserModel update(Long id, UserModel updatedUser) {
    Optional<UserEntity> entity = repository.findById(id);
    entity.ifPresentOrElse(
      p -> {
        p.setName(updatedUser.getName());
        p.setEmail(updatedUser.getEmail());
        repository.save(p);
      },
      () -> System.out.println("User Not Found")
    );
    return assembler.fromEntityToModel(repository.findById(id).orElse(null));
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
