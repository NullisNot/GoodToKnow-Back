package com.example.goodToKnow.auth;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.goodToKnow.adminUser.AdminUser;
import com.example.goodToKnow.adminUser.AdminUserRepository;
import com.example.goodToKnow.adminUser.Role;
import com.example.goodToKnow.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AdminUserRepository adminUserRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthResponse login(LoginRequest request) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    UserDetails user = adminUserRepository.findByUsername(request.getUsername()).orElseThrow();
    String token = jwtService.getToken(user);
    return AuthResponse.builder()
        .token(token)
        .build();

  }

  public AuthResponse register(RegisterRequest request) {
    AdminUser user = AdminUser.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request
            .getPassword()))
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .role(Role.ADMIN)
        .build();

    adminUserRepository.save(user);

    return AuthResponse.builder()
        .token(jwtService.getToken(user))
        .build();
  }
}
