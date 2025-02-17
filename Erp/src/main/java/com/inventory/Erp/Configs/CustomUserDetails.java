package com.inventory.Erp.Configs;

import com.inventory.Erp.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final Users user;
    public CustomUserDetails(Users user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // Return user roles/permissions here if applicable

        return null; // Modify as needed

    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement logic if needed

    }
    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement logic if needed
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement logic if needed
    }
    @Override
    public boolean isEnabled() {
        return user.isActive(); // Check if the user is active
    }
}
