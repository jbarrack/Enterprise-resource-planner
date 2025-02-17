package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PurchaseReturns")
@Data
public class PurchaseReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int returnId;
    @ManyToOne
    @JoinColumn(name = "purchaseOrderId", nullable = true)
    private PurchaseOrder purchaseOrder;
    private java.sql.Date returnDate;
    private int quantity;
    private String reason;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
