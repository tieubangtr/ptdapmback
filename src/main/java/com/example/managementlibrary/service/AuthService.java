package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.LoginRequest;
import com.example.managementlibrary.dto.request.SignupRequest;
import com.example.managementlibrary.dto.request.TokenRefreshRequest;
import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.LoginResponse;
import com.example.managementlibrary.dto.response.TokenRefreshResponse;

public interface AuthService {
    LoginResponse userLogin(LoginRequest loginRequest);
    TokenRefreshResponse refreshToken(TokenRefreshRequest request);

    void userSignup(SignupRequest signupRequest,String token);
}
