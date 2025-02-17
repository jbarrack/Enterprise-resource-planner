package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DeliveryNotes")
@Data
public class DeliveryNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryNoteId;
    @ManyToOne
    @JoinColumn(name = "salesOrderId", nullable = true)
    private SalesOrder salesOrder;
    private java.sql.Date deliveryDate;
    private String status;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
