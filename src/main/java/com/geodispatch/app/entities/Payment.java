package com.geodispatch.app.entities;

import com.geodispatch.app.entities.enums.PaymentMethod;
import com.geodispatch.app.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @CreationTimestamp
    private LocalDateTime paymentTime;

    public Payment() {
    }

    public Payment(Long id,
                   PaymentMethod paymentMethod,
                   Ride ride,
                   Double amount,
                   PaymentStatus paymentStatus,
                   LocalDateTime paymentTime) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.ride = ride;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
    }

    public static PaymentBuilder builder() {
        return new PaymentBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Payment)) {
            return false;
        }

        Payment other = (Payment) obj;

        return java.util.Objects.equals(id, other.id)
                && java.util.Objects.equals(paymentMethod, other.paymentMethod)
                && java.util.Objects.equals(ride, other.ride)
                && java.util.Objects.equals(amount, other.amount)
                && java.util.Objects.equals(paymentStatus, other.paymentStatus)
                && java.util.Objects.equals(paymentTime, other.paymentTime);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
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
        return "Payment{" +
                "id=" + id +
                ", paymentMethod=" + paymentMethod +
                ", ride=" + ride +
                ", amount=" + amount +
                ", paymentStatus=" + paymentStatus +
                ", paymentTime=" + paymentTime +
                '}';
    }
}