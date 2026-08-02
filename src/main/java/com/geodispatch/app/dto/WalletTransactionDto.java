package com.geodispatch.app.dto;

import com.geodispatch.app.entities.enums.TransactionMethod;
import com.geodispatch.app.entities.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.Objects;

public class WalletTransactionDto {

    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;
    private RideDto ride;
    private String transactionId;
    private WalletDto wallet;
    private LocalDateTime timeStamp;

    public WalletTransactionDto() {
    }

    public WalletTransactionDto(Long id,
                                Double amount,
                                TransactionType transactionType,
                                TransactionMethod transactionMethod,
                                RideDto ride,
                                String transactionId,
                                WalletDto wallet,
                                LocalDateTime timeStamp) {
        this.id = id;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionMethod = transactionMethod;
        this.ride = ride;
        this.transactionId = transactionId;
        this.wallet = wallet;
        this.timeStamp = timeStamp;
    }

    public static WalletTransactionDtoBuilder builder() {
        return new WalletTransactionDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionMethod getTransactionMethod() {
        return transactionMethod;
    }

    public void setTransactionMethod(TransactionMethod transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

    public RideDto getRide() {
        return ride;
    }

    public void setRide(RideDto ride) {
        this.ride = ride;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public WalletDto getWallet() {
        return wallet;
    }

    public void setWallet(WalletDto wallet) {
        this.wallet = wallet;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof WalletTransactionDto)) {
            return false;
        }

        WalletTransactionDto other = (WalletTransactionDto) obj;

        return Objects.equals(id, other.id)
                && Objects.equals(amount, other.amount)
                && Objects.equals(transactionType, other.transactionType)
                && Objects.equals(transactionMethod, other.transactionMethod)
                && Objects.equals(ride, other.ride)
                && Objects.equals(transactionId, other.transactionId)
                && Objects.equals(wallet, other.wallet)
                && Objects.equals(timeStamp, other.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
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
        return "WalletTransactionDto{" +
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