package com.geodispatch.app.strategies.impl;

import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.Payment;
import com.geodispatch.app.entities.Rider;
import com.geodispatch.app.entities.enums.PaymentStatus;
import com.geodispatch.app.entities.enums.TransactionMethod;
import com.geodispatch.app.repositories.PaymentRepository;
import com.geodispatch.app.services.WalletService;
import com.geodispatch.app.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletPaymentStrategy
    implements PaymentStrategy {
  private final WalletService walletService;
  private final PaymentRepository paymentRepository;

  public WalletPaymentStrategy(WalletService walletService, PaymentRepository paymentRepository) {
    this.walletService = walletService;
    this.paymentRepository = paymentRepository;
  }

  @Transactional
  public void processPayment(Payment payment) {
    Driver driver = payment.getRide().getDriver();
    Rider rider = payment.getRide().getRider();

    this.walletService.deductMoneyFromWallet(rider.getUser(), payment
        .getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

    double driversCut = payment.getAmount().doubleValue() * (1.0D - PLATFORM_COMMISSION.doubleValue());

    this.walletService.addMoneyToWallet(driver.getUser(),
        Double.valueOf(driversCut), null, payment.getRide(), TransactionMethod.RIDE);

    payment.setPaymentStatus(PaymentStatus.CONFIRMED);
    this.paymentRepository.save(payment);
  }
}