package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.AccountAssembler;
import com.lpogifr.paymybuddy.assembler.SenderAssembler;
import com.lpogifr.paymybuddy.entity.AccountEntity;
import com.lpogifr.paymybuddy.entity.ReceiverEntity;
import com.lpogifr.paymybuddy.entity.ReceiverPrimaryKey;
import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.repository.AccountRepository;
import com.lpogifr.paymybuddy.repository.ReceiverRepository;
import com.lpogifr.paymybuddy.repository.SendersRepository;
import com.lpogifr.paymybuddy.service.SendersService;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SendersServiceImpl implements SendersService {

  private final SendersRepository repository;

  private final AccountRepository accountRepository;

  private final ReceiverRepository receiverRepository;

  private final SenderAssembler assembler;

  private final AccountAssembler accountAssembler;

  @Override
  public List<SenderModel> findAll() {
    return this.assembler.fromEntityListToModelList(repository.findAll());
  }

  @Override
  public SenderModel findByEmail(String email) {
    SenderEntity entity = repository.findByEmail(email);
    return assembler.fromEntityToModel(entity);
  }

  @Override
  public SenderModel findByName(String name) {
    SenderEntity entity = repository.findByName(name);
    return assembler.fromEntityToModel(entity);
  }

  @Override
  public SenderModel findById(Long id) {
    Optional<SenderEntity> entityOptional = repository.findById(id);
    return entityOptional.map(assembler::fromEntityToModel).orElse(null);
  }

  @Override
  public SenderModel save(SenderModel newSender) {
    SenderEntity entityToSave = assembler.fromModelToEntity(newSender);
    AccountEntity accountEntity = accountRepository.save(createAccount(entityToSave));
    entityToSave.setAccount(accountEntity);
    SenderEntity savedSenderEntity = repository.save(entityToSave);
    accountEntity.setSenders(savedSenderEntity);
    accountEntity = accountRepository.save(accountEntity);
    SenderModel saved = assembler.fromEntityToModel(savedSenderEntity);
    saved.setAccount(accountAssembler.fromEntityToModel(accountEntity));
    return saved;
  }

  private AccountEntity createAccount(SenderEntity entityToSave) {
    AccountEntity account = new AccountEntity();
    account.setSenders(entityToSave);
    double leftLimit = 100D;
    double rightLimit = 1000D;
    account.setBalance(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit));
    return account;
  }

  @Override
  public void delete(String email) {
    repository.deleteByEmail(email);
  }

  public SenderModel update(Long id, SenderModel updatedSender) {
    Optional<SenderEntity> entity = repository.findById(id);
    entity.ifPresentOrElse(
      p -> {
        p.setName(updatedSender.getName());
        p.setEmail(updatedSender.getEmail());
        repository.save(p);
      },
      () -> System.out.println("Sender Not Found")
    );
    return assembler.fromEntityToModel(repository.findById(id).orElse(null));
  }

  @Override
  public SenderModel addreceiver(Long id, Long receiverId) {
    Optional<SenderEntity> response = null;

    SenderEntity senderEntity = repository.findById(id).orElse(null);
    SenderEntity newreceiver = repository.findById(receiverId).orElse(null);
    if (senderEntity != null) {
      final var newreceiverEntity = new ReceiverEntity()
        .builder()
        .id(ReceiverPrimaryKey.builder().senderId(senderEntity.getId()).receiverId(newreceiver.getId()).build())
        .sender(senderEntity)
        .receiver(newreceiver)
        .build();
      receiverRepository.save(newreceiverEntity);
      senderEntity.getReceiverList().add(newreceiverEntity);
      repository.save(senderEntity);
      response = Optional.of(senderEntity);
    }
    return assembler.fromEntityToModel(response.orElse(null));
  }

  @Override
  public List<SenderModel> findOtherUSers(Long senderId) {
    return assembler.fromEntityListToModelList(repository.findOtheSender(senderId));
  }
}
