package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "GoodsReturned")
@Data
public class GoodsReturned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int returnId;
    @ManyToOne
    @JoinColumn(name = "salesOrderId", nullable = true)
    private SalesOrder salesOrder;
    private java.sql.Date returnDate;
    private int quantity;
    private String reason;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;
    @Column(nullable = false)
    private String goodsDescription;

    @Column(nullable = false)
    private String condition;

    @PrePersist protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis()); }
}
