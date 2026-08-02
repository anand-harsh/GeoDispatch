package com.geodispatch.app.strategies.impl;
import com.geodispatch.app.entities.Driver;
import com.geodispatch.app.entities.Payment;
import com.geodispatch.app.entities.enums.PaymentStatus;
import com.geodispatch.app.entities.enums.TransactionMethod;
import com.geodispatch.app.repositories.PaymentRepository;
import com.geodispatch.app.services.WalletService; 
import com.geodispatch.app.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class CashPaymentStrategy implements PaymentStrategy {
   private final WalletService walletService;
   private final PaymentRepository paymentRepository;

   public CashPaymentStrategy(WalletService walletService, PaymentRepository paymentRepository) {
      this.walletService = walletService;
      this.paymentRepository = paymentRepository;
   }

   public void processPayment(Payment payment) {
      Driver driver = payment.getRide().getDriver();

      double platformCommission = payment.getAmount().doubleValue() * PLATFORM_COMMISSION.doubleValue();

      this.walletService.deductMoneyFromWallet(driver.getUser(), Double.valueOf(platformCommission), null, payment
            .getRide(), TransactionMethod.RIDE);

      payment.setPaymentStatus(PaymentStatus.CONFIRMED);
      this.paymentRepository.save(payment);
   }
}
