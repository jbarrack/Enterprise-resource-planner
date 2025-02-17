package com.inventory.Erp.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name= "loanType")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanId;
    private String loanName;
    private String loanType;
    private boolean loanStatus;
    private Date localDate;

    public Loan(String loanName, String loanType, boolean loanStatus, Date localDate) {
        this.loanName = loanName;
        this.loanType = loanType;
        this.loanStatus = loanStatus;
        this.localDate = localDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public boolean isLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(boolean loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }
}
