package com.update.splitwse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.update.splitwse.dto.UserRequest;
import com.update.splitwse.entity.User;
import com.update.splitwse.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user (POST /users/register)
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@Valid @RequestBody UserRequest userRequest) {
        User savedUser = userService.registerUser(userRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", savedUser.getUserId());
        response.put("status", "User registered successfully!");

        return ResponseEntity.ok(response);
    }

    // Get user details by userId (GET /users/{userId})
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update user details (PUT /users/{userId})
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}
