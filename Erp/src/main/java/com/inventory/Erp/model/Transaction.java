package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "Transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
    private double amount;
    private String transactionType; // "debit" or "credit"
    private String description;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private Timestamp createdAt;
    @PrePersist protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
}
