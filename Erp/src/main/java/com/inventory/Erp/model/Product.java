package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "Products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private int stock;
    private boolean status;
    @Column(name = "CreatedAt", nullable = false, updatable = false, insertable = false)
    private java.sql.Timestamp createdAt;
}
