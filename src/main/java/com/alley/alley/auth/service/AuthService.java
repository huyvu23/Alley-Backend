package com.alley.alley.auth.service;

import com.alley.alley.auth.dto.AuthResponse;
import com.alley.alley.auth.dto.LoginRequest;
import com.alley.alley.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
