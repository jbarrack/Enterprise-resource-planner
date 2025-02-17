package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SalesQuotations")
@Data
public class SalesQuotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quotationId;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;
    private java.sql.Date quotationDate;
    private java.math.BigDecimal totalAmount;
    private String salesquotationDetails;
    private boolean status;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;

}
