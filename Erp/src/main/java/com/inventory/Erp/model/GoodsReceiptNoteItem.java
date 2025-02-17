package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "GoodsReceiptNoteItems")
@Data
public class GoodsReceiptNoteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; @ManyToOne
    @JoinColumn(name = "goodsReceiptNoteId", nullable = false)
    private GoodsReceiptNote goodsReceiptNote;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product; private int quantity;
}
