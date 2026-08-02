package com.geodispatch.app.dto;

import java.util.List;
import java.util.Objects;

public class WalletDto {

    private Long id;
    private UserDto user;
    private Double balance;
    private List<WalletTransactionDto> transactions;

    public WalletDto() {
    }

    public WalletDto(Long id,
                     UserDto user,
                     Double balance,
                     List<WalletTransactionDto> transactions) {
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<WalletTransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<WalletTransactionDto> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof WalletDto)) {
            return false;
        }

        WalletDto other = (WalletDto) obj;

        return Objects.equals(id, other.id)
                && Objects.equals(user, other.user)
                && Objects.equals(balance, other.balance)
                && Objects.equals(transactions, other.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, balance, transactions);
    }

    @Override
    public String toString() {
        return "WalletDto{" +
                "id=" + id +
                ", user=" + user +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}