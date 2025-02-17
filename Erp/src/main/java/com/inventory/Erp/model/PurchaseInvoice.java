package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PurchaseInvoices")
@Data
public class PurchaseInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseInvoiceId;
    @ManyToOne
    @JoinColumn(name = "purchaseOrderId", nullable = true)
    private PurchaseOrder purchaseOrder;
    private java.sql.Date invoiceDate;
    private java.math.BigDecimal totalAmount;
    private String status;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
