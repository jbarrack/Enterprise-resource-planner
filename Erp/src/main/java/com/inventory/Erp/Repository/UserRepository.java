package com.inventory.Erp.Repository;

import com.inventory.Erp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Users findUserByUsername(String username);
}
