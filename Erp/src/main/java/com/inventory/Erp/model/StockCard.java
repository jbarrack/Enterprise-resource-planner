package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "StockCard")
@Data
public class StockCard  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_card_id")
    private int stockCardId;
    @ManyToOne
    @JoinColumn(name = "productId", nullable = true)
    private Product product;
    private int quantity;
    @Column(name = "LastUpdated", nullable = false, insertable = false)
    private java.sql.Timestamp lastUpdated;
}
