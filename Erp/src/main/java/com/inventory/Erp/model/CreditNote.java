package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CreditNotes")
@Data
public class CreditNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int creditNoteId;
    @ManyToOne
    @JoinColumn(name = "invoiceId", nullable = true)
    private SalesInvoice invoice;
    private java.sql.Date creditDate;
    private java.math.BigDecimal amount;
    private String reason;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
