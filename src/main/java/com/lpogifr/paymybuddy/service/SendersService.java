package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.model.SenderModel;
import java.util.List;

public interface SendersService {
  /**
   * Find all Senders in Database
   * @return List<SenderEntity>
   */
  List<SenderModel> findAll();
  SenderModel findByEmail(String email);

  SenderModel findByName(String name);

  SenderModel findById(Long id);

  SenderModel save(SenderModel newSender);

  void delete(String email);

  SenderModel update(Long id, SenderModel updatedSender);

  SenderModel addreceiver(Long id, Long receiverId);

  List<SenderModel> findOtherUSers(Long senderId);
}
