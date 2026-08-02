package com.geodispatch.app.strategies;
import com.geodispatch.app.entities.Payment;

public interface PaymentStrategy {
    public static final Double PLATFORM_COMMISSION = Double.valueOf(0.3D);

    void processPayment(Payment paramPayment);
}
