package com.example.railway.service.impl;

import com.example.railway.entity.User;
import com.example.railway.dto.LoginRequest;
import com.example.railway.dto.ApiResponse;
import com.example.railway.repository.UserRepository;
import com.example.railway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return new ApiResponse(false, "Email already exists", null);
        }
        User savedUser = userRepository.save(user);
        return new ApiResponse(true, "User registered successfully", savedUser);
    }

    @Override
    public ApiResponse loginUser(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(request.getPassword())) {
                return new ApiResponse(true, "Login successful", user);
            }
        }
        return new ApiResponse(false, "Invalid credentials", null);
    }

    @Override
    public ApiResponse getUserProfile(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            return new ApiResponse(true, "Profile fetched", userOpt.get());
        }
        return new ApiResponse(false, "User not found", null);
    }

    @Override
    public ApiResponse updatePassword(Long id, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userRepository.save(user);
                return new ApiResponse(true, "Password updated successfully", null);
            }
            return new ApiResponse(false, "Incorrect old password", null);
        }
        return new ApiResponse(false, "User not found", null);
    }
}
