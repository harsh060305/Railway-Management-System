package com.example.railway.service;

import com.example.railway.entity.User;
import com.example.railway.dto.LoginRequest;
import com.example.railway.dto.ApiResponse;

public interface UserService {
    ApiResponse registerUser(User user);
    ApiResponse loginUser(LoginRequest request);
    ApiResponse getUserProfile(Long id);
    ApiResponse updatePassword(Long id, String oldPassword, String newPassword);
}
