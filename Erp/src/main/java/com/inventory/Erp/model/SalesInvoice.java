package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "SalesInvoices")
@Data
public class SalesInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceId;
    @ManyToOne
    @JoinColumn(name = "salesOrderId", nullable = true)
    private SalesOrder salesOrder;
    private Date invoiceDate;
    private double totalAmount;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = true)
    private Customer customer;
    private int quantityDelivered;

    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
