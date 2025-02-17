package com.inventory.Erp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "PurchaseOrders")
@Data
public class PurchaseOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseOrderId;
    @ManyToOne
    @JoinColumn(name = "supplierId", nullable = true)
    private Supplier supplier;
    @NotNull
    private java.sql.Date orderDate;
    @NotNull
    private java.math.BigDecimal totalAmount;
    @NotNull
    private boolean status;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseDetail> items;
    private java.math.BigDecimal vat;
    private java.math.BigDecimal subtotal;
}
