package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PurchaseDetails")
@Data
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseDetailId;
    @ManyToOne
    @JoinColumn(name = "purchaseOrderId", nullable = true)
    private PurchaseOrder purchaseOrder;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product;
    private int quantity;
    private java.math.BigDecimal unitPrice;
}
