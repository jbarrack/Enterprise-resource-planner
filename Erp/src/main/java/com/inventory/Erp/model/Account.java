package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private String accountName;
    private boolean isBusinessAccount;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public void setBusinessAccount(boolean isBusinessAccount) {
        this.isBusinessAccount = isBusinessAccount;
    }
    public boolean isBusinessAccount() {
        return isBusinessAccount; }
}
