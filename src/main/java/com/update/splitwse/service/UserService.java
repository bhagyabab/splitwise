package com.update.splitwse.service;

import com.update.splitwse.dto.UserRequest;
import com.update.splitwse.entity.User;
import com.update.splitwse.exception.UserNotFoundException;
import com.update.splitwse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user using UserRequest (DTO)
    public User registerUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhone(userRequest.getPhone());
        return userRepository.save(user);
    }

    // Get user details by userId
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Get user by email (for login purposes)
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                             .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    // Update user details
    public User updateUser(Long userId, User updatedUser) {
        // Check if the user exists
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Update the user fields with new data
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setPassword(updatedUser.getPassword());

        // Save and return the updated user
        return userRepository.save(existingUser);
    }

    // List all users (optional, if needed)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get multiple users by their IDs
    public List<User> getAllUsersByIds(List<Long> userIds) {
        return userIds.stream()
                      .map(userRepository::findById)
                      .filter(Optional::isPresent)
                      .map(Optional::get)
                      .collect(Collectors.toList());
    }
}
