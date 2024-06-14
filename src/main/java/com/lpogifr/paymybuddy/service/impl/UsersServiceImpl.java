package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.BankAccountAssembler;
import com.lpogifr.paymybuddy.assembler.UserAssembler;
import com.lpogifr.paymybuddy.dto.UsersDto;
import com.lpogifr.paymybuddy.entity.BankAccountEntity;
import com.lpogifr.paymybuddy.entity.UserEntity;
import com.lpogifr.paymybuddy.model.UserModel;
import com.lpogifr.paymybuddy.repository.BankAccountRepository;
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

  private final UserAssembler assembler;

  private final BankAccountAssembler bankAccountAssembler;

  @Override
  public List<UserModel> findAll() {
    return this.assembler.fromEntityListToModelList(this.repository.findAll());
  }

  @Override
  public UserModel findByEmail(String email) {
    UserEntity entity = this.repository.findByEmail(email);
    return this.assembler.fromEntityToModel(entity);
  }

  @Override
  public UserModel findById(Long id) {
    Optional<UserEntity> entityOptional = this.repository.findById(id);
    return entityOptional.map(assembler::fromEntityToModel).orElse(null);
  }

  @Override
  public UserModel save(UserModel newUser) {
    UserEntity entityToSave = this.assembler.fromModelToEntity(newUser);
    BankAccountEntity bankAccountEntity = this.bankAccountRepository.save(this.createBankAccount(entityToSave));
    entityToSave.setBankAccount(bankAccountEntity);
    UserEntity savedUserEntity = repository.save(entityToSave);
    bankAccountEntity.setUsers(savedUserEntity);
    bankAccountEntity = this.bankAccountRepository.save(bankAccountEntity);
    UserModel saved = this.assembler.fromEntityToModel(savedUserEntity);
    saved.setBankAccount(this.bankAccountAssembler.fromEntityToModel(bankAccountEntity));
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
    UserEntity entityToSave = this.assembler.fromModelToEntity(updatedUser);
    return this.assembler.fromEntityToModel(repository.save(entityToSave));
  }
}
