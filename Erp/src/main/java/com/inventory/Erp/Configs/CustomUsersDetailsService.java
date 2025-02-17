package com.inventory.Erp.Configs;

import com.inventory.Erp.Repository.UserRepository;
import com.inventory.Erp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUsersDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User  not found with username: " + username);
        }
        return new CustomUserDetails(user);

    }
}
