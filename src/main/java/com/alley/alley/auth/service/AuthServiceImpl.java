package com.alley.alley.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alley.alley.auth.dto.AuthResponse;
import com.alley.alley.auth.dto.LoginRequest;
import com.alley.alley.auth.dto.RegisterRequest;
import com.alley.alley.security.JwtService;
import com.alley.alley.user.entity.User;
import com.alley.alley.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        @Override
        public AuthResponse register(RegisterRequest request) {
                // Check if email already exists
                if (userRepository.existsByEmail(request.getEmail())) {
                        throw new RuntimeException("Email already registered");
                }

                // Check if phone number already exists
                if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
                        throw new RuntimeException("Phone number already registered");
                }

                // Build and save the user
                User user = User.builder()
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .phoneNumber(request.getPhoneNumber())
                                .build();

                User savedUser = userRepository.save(user);

                // Generate JWT token
                String token = jwtService.generateToken(savedUser);

                return AuthResponse.builder()
                                .token(token)
                                .userId(savedUser.getId())
                                .email(savedUser.getEmail())
                                .firstName(savedUser.getFirstName())
                                .lastName(savedUser.getLastName())
                                .build();
        }

        @Override
        public AuthResponse login(LoginRequest request) {
                // Authenticate the user (throws exception if invalid)
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));

                // Find the user
                User user = userRepository.findByEmail(request.getEmail())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // Generate JWT token
                String token = jwtService.generateToken(user);

                return AuthResponse.builder()
                                .token(token)
                                .userId(user.getId())
                                .email(user.getEmail())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .build();
        }
}
