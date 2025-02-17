package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.AccountRepository;
import com.inventory.Erp.model.Account;
import com.inventory.Erp.model.AccountType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void init() {
        Optional<Account> businessAccount = accountRepository.findByIsBusinessAccount(true);
        if(!businessAccount.isPresent()) {
            Account newBusinessAccount = new Account();
            newBusinessAccount.setAccountName("Business Account");
            newBusinessAccount.setAccountType(AccountType.REVENUE);
            newBusinessAccount.setBusinessAccount(true);
            accountRepository.save(newBusinessAccount);
        }
    }
    public Account getBusinessAccount() {
        return accountRepository.findByIsBusinessAccount(true).orElseThrow(() -> new RuntimeException("Business account not found"));
    }
       public Account createAccount(String accountName, AccountType accountType) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountType(accountType);
        return accountRepository.save(account);
    }
    }

