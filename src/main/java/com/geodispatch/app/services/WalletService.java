package com.geodispatch.app.services;

import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.User;
import com.geodispatch.app.entities.Wallet;
import com.geodispatch.app.entities.enums.TransactionMethod;

public interface WalletService {
  Wallet addMoneyToWallet(User paramUser, Double paramDouble, String paramString, Ride paramRide, TransactionMethod paramTransactionMethod);
  
  Wallet deductMoneyFromWallet(User paramUser, Double paramDouble, String paramString, Ride paramRide, TransactionMethod paramTransactionMethod);
  
  void withdrawAllMyMoneyFromWallet();
  
  Wallet findWalletById(Long paramLong);
  
  Wallet createNewWallet(User paramUser);
  
  Wallet findByUser(User paramUser);
}


