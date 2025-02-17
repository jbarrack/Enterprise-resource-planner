package com.inventory.Erp.Repository;

import com.inventory.Erp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Override
    Optional<Product> findById(Long aLong);
}
