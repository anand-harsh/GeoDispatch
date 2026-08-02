package com.geodispatch.app.services.impl;

import com.geodispatch.app.entities.Payment;
import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.enums.PaymentStatus;
import com.geodispatch.app.exceptions.ResourceNotFoundException;
import com.geodispatch.app.repositories.PaymentRepository;
import com.geodispatch.app.services.PaymentService;
import com.geodispatch.app.strategies.PaymentStrategyManager;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
   public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentStrategyManager paymentStrategyManager) {
      this.paymentRepository = paymentRepository;
      this.paymentStrategyManager = paymentStrategyManager;
   }

   private final PaymentRepository paymentRepository;

   private final PaymentStrategyManager paymentStrategyManager;

   public void processPayment(Ride ride) {
      Payment payment = (Payment) this.paymentRepository.findByRide(ride)
            .orElseThrow(() -> new ResourceNotFoundException("Payment not found for ride with id: " + ride.getId()));
      this.paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
   }

   public Payment createNewPayment(Ride ride) {
      Payment payment = Payment.builder().ride(ride).paymentMethod(ride.getPaymentMethod()).amount(ride.getFare())
            .paymentStatus(PaymentStatus.PENDING).build();
      return (Payment) this.paymentRepository.save(payment);
   }

   public void updatePaymentStatus(Payment payment, PaymentStatus status) {
      payment.setPaymentStatus(status);
      this.paymentRepository.save(payment);
   }
}
