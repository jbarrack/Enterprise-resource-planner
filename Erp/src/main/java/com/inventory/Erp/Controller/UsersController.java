package com.inventory.Erp.Controller;

import com.inventory.Erp.Services.UserService;
import com.inventory.Erp.model.LoginRequest;
import com.inventory.Erp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {
    @RestController
    @RequestMapping("/api/users")
    public class UserController {
        @Autowired
        private UserService userService;

        @Autowired
        private AuthenticationManager authenticationManager;

        public UserController(UserService userService, AuthenticationManager authenticationManager) {
            this.userService = userService;
            this.authenticationManager = authenticationManager;
        }
        @RequestMapping(value = "/register", method = RequestMethod.POST)
        public ResponseEntity<String> registerUser(@RequestBody Users user) {
            try {
                userService.CreateNewUser(user);
                return ResponseEntity.ok("User registered successfully!");
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        @RequestMapping(value = "/login", method = RequestMethod.POST)
        public ResponseEntity<String> loginUser (@RequestBody LoginRequest loginRequest) {
            try {
                Authentication authentication = authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                return ResponseEntity.ok("User {username}  logged in successfully!");
            } catch (AuthenticationException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        }
        @RequestMapping(value = "/allUsers",method = RequestMethod.GET)
        public ResponseEntity<List<Users>> getAllusers(){
            List<Users> getAllUsers = userService.getAllusers();
            return new ResponseEntity<>(getAllUsers,HttpStatus.OK);
        }
        @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
        public ResponseEntity<?>getUserById(@PathVariable("id") long id){
            Users finduserById= userService.findUserById(id);
            return new ResponseEntity<>(finduserById, HttpStatus.OK);
            
        }

    }
};


