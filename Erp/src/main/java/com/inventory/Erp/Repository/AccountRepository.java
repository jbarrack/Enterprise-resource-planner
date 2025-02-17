package com.inventory.Erp.Repository;

import com.inventory.Erp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByIsBusinessAccount(boolean isBusinessAccount);
    Optional<Account> findByIsBusinessAccount(boolean isBusinessAccount);
}
