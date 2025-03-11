package com.inventory.Erp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private PurchaseOrder purchaseOrder;
    @NotNull
    private java.sql.Date returnDate;
    @NotNull
    private int quantity;
    @NotNull
    private String reason;
    @NotNull
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
