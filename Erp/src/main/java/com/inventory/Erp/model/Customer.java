package com.inventory.Erp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "Customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String contact;
    private boolean active;

    @Column(name = "sales_representative")
    private String salesRepresentative;

    @Column(name = "krapin")
    private String kraPIN;

    @Column(name = "local_date")
    private LocalDate localDate;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
}

