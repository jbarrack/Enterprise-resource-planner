package com.inventory.Erp.Repository;

import com.inventory.Erp.model.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Integer> {
    List<SalesInvoice> findByCustomerCustomerId(int customerId);
    List<SalesInvoice> findByInvoiceDateBetween(Date startDate, Date endDate);
}
