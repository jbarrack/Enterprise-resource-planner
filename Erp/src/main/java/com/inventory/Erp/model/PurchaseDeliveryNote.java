package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PurchaseDeliveryNotes")
@Data
public class PurchaseDeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryNoteId;
    @ManyToOne
    @JoinColumn(name = "purchaseOrderId", nullable = true)
    private PurchaseOrder purchaseOrder;
    private java.sql.Date deliveryDate;
    private String status;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
