package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.entity.SenderEntity;
import java.util.List;

public interface ReceiverService {
  /**
   * Find all receiver in Database
   * @return List<receiverEntity>
   */
  List<SenderEntity> findAll();
}
