package com.inventory.Erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String username;
    private String email;
    private String password;
    private String contact;
    private boolean active;
    private Date locatdate;

    private Users(){
        super();
    }

    public Users(String username, String email, String password, String contact, boolean active, Date locatdate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.active = active;
        this.locatdate = locatdate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getLocatdate() {
        return locatdate;
    }

    public void setLocatdate(Date locatdate) {
        this.locatdate = locatdate;
    }
}
