package com.lpogifr.paymybuddy.service;

import com.lpogifr.paymybuddy.entity.TransactionsEntity;
import com.lpogifr.paymybuddy.model.TransactionsModel;
import java.util.List;

public interface TransactionsService {
  List<TransactionsEntity> findAll();

  TransactionsModel save(TransactionsModel newTransactions);
}
