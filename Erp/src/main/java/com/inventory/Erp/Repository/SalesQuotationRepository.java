package com.inventory.Erp.Repository;

import com.inventory.Erp.model.SalesQuotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesQuotationRepository extends JpaRepository<SalesQuotation, Integer> {

}
