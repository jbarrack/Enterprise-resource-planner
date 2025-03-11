package com.inventory.Erp.Repository;

import com.inventory.Erp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByActive(boolean active);
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> findCustomerByContact(String contact);
    Optional<Customer> findCustomerByEmailAndContact(String email,String contact);
}
