package com.geodispatch.app.dto;

import com.geodispatch.app.entities.enums.TransactionMethod;
import com.geodispatch.app.entities.enums.TransactionType;

import java.time.LocalDateTime;

public class WalletTransactionDtoBuilder {

    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto ride;
    private String transactionId;
    private WalletDto wallet;
    private LocalDateTime timeStamp;

    public WalletTransactionDtoBuilder() {
    }

    public WalletTransactionDtoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public WalletTransactionDtoBuilder amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public WalletTransactionDtoBuilder transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public WalletTransactionDtoBuilder transactionMethod(TransactionMethod transactionMethod) {
        this.transactionMethod = transactionMethod;
        return this;
    }

    public WalletTransactionDtoBuilder ride(RideDto ride) {
        this.ride = ride;
        return this;
    }

    public WalletTransactionDtoBuilder transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public WalletTransactionDtoBuilder wallet(WalletDto wallet) {
        this.wallet = wallet;
        return this;
    }

    public WalletTransactionDtoBuilder timeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public WalletTransactionDto build() {
        return new WalletTransactionDto(
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
        return "WalletTransactionDtoBuilder{" +
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