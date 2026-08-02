package com.geodispatch.app.services.impl;

import com.geodispatch.app.entities.WalletTransaction;
import com.geodispatch.app.repositories.WalletTransactionRepository;
import com.geodispatch.app.services.WalletTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {
   public WalletTransactionServiceImpl(WalletTransactionRepository walletTransactionRepository,
         ModelMapper modelMapper) {
      this.walletTransactionRepository = walletTransactionRepository;
      this.modelMapper = modelMapper;
   }

   private final WalletTransactionRepository walletTransactionRepository;
   private final ModelMapper modelMapper;

   public void createNewWalletTransaction(WalletTransaction walletTransaction) {
      this.walletTransactionRepository.save(walletTransaction);
   }
}
