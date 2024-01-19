package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.entity.FriendEntity;
import java.util.List;

public interface FriendService {
  /**
   * Find all Friend in Database
   * @return List<FriendEntity>
   */
  List<FriendEntity> findAll();
}
