package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.AccountAssembler;
import com.lpogifr.paymybuddy.assembler.UserAssembler;
import com.lpogifr.paymybuddy.entity.AccountEntity;
import com.lpogifr.paymybuddy.entity.ReceiverEntity;
import com.lpogifr.paymybuddy.entity.ReceiverPrimaryKey;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.repository.AccountRepository;
import com.lpogifr.paymybuddy.repository.ReceiverRepository;
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

  private final ReceiverRepository receiverRepository;

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
  public UserModel addreceiver(Long id, Long receiverId) {
    Optional<UserEntity> response = null;
    UserEntity userEntity = repository.findById(id).orElse(null);
    UserEntity newreceiver = repository.findById(receiverId).orElse(null);
    if (userEntity != null) {
      final var newreceiverEntity = new ReceiverEntity()
        .builder()
        .id(ReceiverPrimaryKey.builder().userId(userEntity.getId()).receiverId(newreceiver.getId()).build())
        .user(userEntity)
        .receiver(newreceiver)
        .build();
      List<ReceiverEntity> receiverEntityList = userEntity.getReceiverList();
      receiverRepository.save(newreceiverEntity);
      //userEntity.setreceiverList(receiverEntityList);
      response = repository.findById(id);
    }
    return assembler.fromEntityToModel(response.orElse(null));
  }

  @Override
  public List<UserModel> findOtherUSers(Long userId) {
    return assembler.fromEntityListToModelList(repository.findOtheUser(userId));
  }
}
