package com.geodispatch.app.entities;

import com.geodispatch.app.entities.enums.TransactionMethod;
import com.geodispatch.app.entities.enums.TransactionType;

import java.time.LocalDateTime;

public class WalletTransactionBuilder {

    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private Ride ride;
    private String transactionId;
    private Wallet wallet;
    private LocalDateTime timeStamp;

    public WalletTransactionBuilder() {
    }

    public WalletTransactionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public WalletTransactionBuilder amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public WalletTransactionBuilder transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public WalletTransactionBuilder transactionMethod(TransactionMethod transactionMethod) {
        this.transactionMethod = transactionMethod;
        return this;
    }

    public WalletTransactionBuilder ride(Ride ride) {
        this.ride = ride;
        return this;
    }

    public WalletTransactionBuilder transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public WalletTransactionBuilder wallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public WalletTransactionBuilder timeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public WalletTransaction build() {
        return new WalletTransaction(
                id,
                amount,
                transactionType,
                transactionMethod,
                ride,
                transactionId,
                wallet,
                timeStamp
        );
    }

    @Override
    public String toString() {
        return "WalletTransactionBuilder{" +
                "id=" + id +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", transactionMethod=" + transactionMethod +
                ", ride=" + ride +
                ", transactionId='" + transactionId + '\'' +
                ", wallet=" + wallet +
                ", timeStamp=" + timeStamp +
                '}';
    }
}