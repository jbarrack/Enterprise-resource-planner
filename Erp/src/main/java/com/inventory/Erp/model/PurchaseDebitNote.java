package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="PurchaseDebitNotes")
@Data
public class PurchaseDebitNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int debitNoteId;
    @ManyToOne
    @JoinColumn(name = "purchaseInvoiceId", nullable = true)
    private PurchaseInvoice purchaseInvoice;
    private java.sql.Date debitDate;
    private java.math.BigDecimal amount;
    private String reason;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
