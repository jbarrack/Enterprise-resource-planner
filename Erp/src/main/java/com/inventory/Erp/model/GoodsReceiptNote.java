package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "GoodsReceiptNotes")
@Data
public class GoodsReceiptNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "supplierId", nullable = false)
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "purchaseOrderId", nullable = false)
    private PurchaseOrder purchaseOrder;
    private java.sql.Date receivedDate;
    @OneToMany(mappedBy = "goodsReceiptNote", cascade = CascadeType.ALL)
    private List<GoodsReceiptNoteItem> items;
}
