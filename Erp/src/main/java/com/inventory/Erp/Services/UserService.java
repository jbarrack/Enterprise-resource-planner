package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.UserRepository;
import com.inventory.Erp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
     @Autowired
     private PasswordEncoder passwordEncoder;
     @Autowired
     private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean checkIfUser_Exist(String username,String email){
        return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);

    }
    public List<Users> getAllusers(){
        return userRepository.findAll();
    }
    public Users CreateNewUser(Users user){
     if(checkIfUser_Exist(user.getUsername(), user.getEmail())){
         throw new IllegalArgumentException("Try again,details missing");
     }
     user.setActive(false);
     user.setContact(user.getContact());
     user.setEmail(user.getEmail());
     user.setLocatdate(user.getLocatdate());
     user.setPassword(passwordEncoder.encode(user.getPassword()));
     return userRepository.save(user);
    }
    public Users findUsersByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public Users findUserById(long id){
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User not found"));
    }

}
