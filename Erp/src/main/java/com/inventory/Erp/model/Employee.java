package com.inventory.Erp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;
    @NotNull
    private String firstName;
    @NotNull
    private String middleName;
    @NotNull
    private String surname;
    @NotNull
    private boolean status;
    @NotNull
    private String email;
    @NotNull
    private String gender;
    @NotNull
    private String contact;
    @NotNull
    private String professional;
    @NotNull
    private String contractType;
    @NotNull
    private Date employmentDate;

}
