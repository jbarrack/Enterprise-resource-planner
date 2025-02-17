package com.inventory.Erp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "SalesOrders")
@Data
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long salesOrderId;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customers;
    private java.sql.Date orderDate;
    private double totalAmount;
    private String status;
    private String itemsDescriptions;
    private int quantity;
    private double unitprice;
    private float  vat;
    private double discountAllowed;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product;
}
