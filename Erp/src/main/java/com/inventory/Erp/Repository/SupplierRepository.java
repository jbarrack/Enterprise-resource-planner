package com.inventory.Erp.Repository;

import com.inventory.Erp.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
  Optional<Supplier> findSupplierByKraPIN(String kraPIN);
}
