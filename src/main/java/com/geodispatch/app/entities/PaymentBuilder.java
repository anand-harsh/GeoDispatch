package com.geodispatch.app.entities;

import com.geodispatch.app.entities.enums.PaymentMethod;
import com.geodispatch.app.entities.enums.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentBuilder {

    private Long id;
    private PaymentMethod paymentMethod;
    private Ride ride;
    private Double amount;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentTime;

    public PaymentBuilder() {
    }

    public PaymentBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public PaymentBuilder paymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public PaymentBuilder ride(Ride ride) {
        this.ride = ride;
        return this;
    }

    public PaymentBuilder amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public PaymentBuilder paymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public PaymentBuilder paymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
        return this;
    }

    public Payment build() {
        return new Payment(
                id,
                paymentMethod,
                ride,
                amount,
                paymentStatus,
                paymentTime
        );
    }

    @Override
    public String toString() {
        return "PaymentBuilder{" +
                "id=" + id +
                ", paymentMethod=" + paymentMethod +
                ", ride=" + ride +
                ", amount=" + amount +
                ", paymentStatus=" + paymentStatus +
                ", paymentTime=" + paymentTime +
                '}';
    }
}