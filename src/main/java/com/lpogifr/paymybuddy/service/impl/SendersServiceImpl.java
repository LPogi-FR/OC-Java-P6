package com.lpogifr.paymybuddy.service.impl;

import com.lpogifr.paymybuddy.assembler.SenderAssembler;
import com.lpogifr.paymybuddy.entity.ReceiverEntity;
import com.lpogifr.paymybuddy.entity.ReceiverPrimaryKey;
import com.lpogifr.paymybuddy.entity.SenderEntity;
import com.lpogifr.paymybuddy.exception.ExistingEmailException;
import com.lpogifr.paymybuddy.front.form.RegisterForm;
import com.lpogifr.paymybuddy.model.AccountModel;
import com.lpogifr.paymybuddy.model.SenderModel;
import com.lpogifr.paymybuddy.repository.ReceiverRepository;
import com.lpogifr.paymybuddy.repository.SendersRepository;
import com.lpogifr.paymybuddy.service.SendersService;
import com.mysql.cj.callback.MysqlCallbackHandler;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SendersServiceImpl implements SendersService {

  private final SendersRepository repository;

  private final ReceiverRepository receiverRepository;

  private final SenderAssembler assembler;

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
  public SenderModel save(SenderModel model) {
    repository.save(assembler.fromModelToEntity(model));
    return model;
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
  public SenderModel addReceiver(Long id, Long receiverId) {
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

  @Override
  public void createSender(RegisterForm registerForm, AccountModel newAccount) {
    Optional<SenderEntity> entity = Optional.ofNullable(repository.findByEmail(registerForm.getEmail()));

    entity.ifPresentOrElse(
      p -> {
        throw new ExistingEmailException("Email : " + p.getEmail() + " already exisnt in DB");
      },
      () -> {
        SenderModel senderModel = new SenderModel();
        senderModel.setEmail(registerForm.getEmail());
        senderModel.setName(registerForm.getName());
        senderModel.setPassword(registerForm.getPassword());
        senderModel.setAccount(newAccount);
        save(senderModel);
      }
    );
  }
}
