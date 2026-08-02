package com.geodispatch.app.services;

import com.geodispatch.app.entities.Payment;
import com.geodispatch.app.entities.Ride;
import com.geodispatch.app.entities.enums.PaymentStatus;

public interface PaymentService {
  void processPayment(Ride paramRide);
  
  Payment createNewPayment(Ride paramRide);
  
  void updatePaymentStatus(Payment paramPayment, PaymentStatus paramPaymentStatus);
}
