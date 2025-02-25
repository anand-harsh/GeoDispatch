package com.floxcon.project.caver.fccabApp.entities;

import com.floxcon.project.caver.fccabApp.entities.enums.TransactionMethod;
import com.floxcon.project.caver.fccabApp.entities.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;

    @OneToOne
    private Ride ride;

    private String transactionId;

    @ManyToOne
    private Wallet wallet;


    @CreationTimestamp
    private LocalDateTime createdAt;
}
