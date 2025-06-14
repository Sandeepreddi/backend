package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://magenta-zabaione-9a3e87.netlify.app")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ✅ Create new user
    @PostMapping("/users")
public ResponseEntity<?> createUser(@RequestBody User newUser) {
    // Check if email already exists
    if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
        return ResponseEntity.status(409).body("Email already exists!");
    }
    
    User savedUser = userRepository.save(newUser);
    return ResponseEntity.ok(savedUser);
}
   

    // ✅ Login: fetch user by email
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("User not found with email: " + loginRequest.getEmail());
        }
    }

    // ✅ Update user's blogs
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUserBlogs(@PathVariable String userId, @RequestBody User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent()) {
            return ResponseEntity.status(404).body("User not found with id: " + userId);
        }

        User user = existingUser.get();
        user.setBlogs(updatedUser.getBlogs());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // ✅ Get user by ID
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.status(404).body("User not found with id: " + userId);
        }
        return ResponseEntity.ok(user.get());
    }

    // ✅ Delete user by ID
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.status(404).body("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // ✅ Get all users
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
