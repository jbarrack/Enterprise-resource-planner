package com.inventory.Erp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "Customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int customerId;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    @NaturalId
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String contact;
    @NotNull
    private boolean active;
    @NotNull
    @Column(name = "sales_representative")
    private String salesRepresentative;

    @Column(name = "krapin")
    @NotNull
    private String kraPIN;

    @Column(name = "local_date")
    @NotNull
    private LocalDate localDate;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    @NotNull
    @CreationTimestamp
    private Timestamp createdAt;
}

