package com.geodispatch.app.services.impl;

import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.User;
import com.geodispatch.app.entities.Wallet;
import com.geodispatch.app.entities.WalletTransaction;
import com.geodispatch.app.entities.enums.TransactionMethod;
import com.geodispatch.app.entities.enums.TransactionType;
import com.geodispatch.app.exceptions.ResourceNotFoundException;
import com.geodispatch.app.repositories.WalletRepository;
import com.geodispatch.app.services.WalletService;
import com.geodispatch.app.services.WalletTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl implements WalletService {
   private final WalletRepository walletRepository;

   public WalletServiceImpl(WalletRepository walletRepository, WalletTransactionService walletTransactionService,
         ModelMapper modelMapper) {
      this.walletRepository = walletRepository;
      this.walletTransactionService = walletTransactionService;
      this.modelMapper = modelMapper;
   }

   private final WalletTransactionService walletTransactionService;

   private final ModelMapper modelMapper;

   @Transactional
   public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride,
         TransactionMethod transactionMethod) {
      Wallet wallet = findByUser(user);
      wallet.setBalance(Double.valueOf(wallet.getBalance().doubleValue() + amount.doubleValue()));

      WalletTransaction walletTransaction = WalletTransaction.builder().transactionId(transactionId).ride(ride)
            .wallet(wallet).transactionType(TransactionType.CREDIT).transactionMethod(transactionMethod).amount(amount)
            .build();

      this.walletTransactionService.createNewWalletTransaction(walletTransaction);

      return (Wallet) this.walletRepository.save(wallet);
   }

   @Transactional
   public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride,
         TransactionMethod transactionMethod) {
      Wallet wallet = findByUser(user);
      wallet.setBalance(Double.valueOf(wallet.getBalance().doubleValue() - amount.doubleValue()));

      WalletTransaction walletTransaction = WalletTransaction.builder().transactionId(transactionId).ride(ride)
            .wallet(wallet).transactionType(TransactionType.DEBIT).transactionMethod(transactionMethod).amount(amount)
            .build();

      this.walletTransactionService.createNewWalletTransaction(walletTransaction);

      return (Wallet) this.walletRepository.save(wallet);
   }

   public void withdrawAllMyMoneyFromWallet() {
   }

   public Wallet findWalletById(Long walletId) {
      return (Wallet) this.walletRepository.findById(walletId)
            .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));
   }

   public Wallet createNewWallet(User user) {
      Wallet wallet = new Wallet();
      wallet.setUser(user);
      return (Wallet) this.walletRepository.save(wallet);
   }

   public Wallet findByUser(User user) {
      return (Wallet) this.walletRepository.findByUser(user)
            .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for user with id: " + user.getId()));
   }
}
