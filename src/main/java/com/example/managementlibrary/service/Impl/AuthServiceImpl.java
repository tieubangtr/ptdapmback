package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.common.ERole;
import com.example.managementlibrary.dto.request.*;
import com.example.managementlibrary.dto.response.LoginResponse;
import com.example.managementlibrary.dto.response.TokenRefreshResponse;
import com.example.managementlibrary.entity.RefreshToken;
import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.security.JwtUtils;
import com.example.managementlibrary.security.MyUser;
import com.example.managementlibrary.service.AuthService;
import com.example.managementlibrary.service.RefreshTokenService;
import com.example.managementlibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserService userService;

    @Override
    public LoginResponse userLogin(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            MyUser userDetails = (MyUser) authentication.getPrincipal();
            String jwt = jwtUtils.generateJwtToken(authentication);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            LoginResponse loginResponse = new LoginResponse(userDetails.getId(), userDetails.getUsername(), roles, jwt, refreshToken.getToken());
            return loginResponse;
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }

    }



    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new GenericException(
                        "Refresh token is not in database!"));


    }

    @Override
    public void userSignup(SignupRequest signupRequest,String token) {
        Set<RoleRequest> roleRequests=new HashSet<>();
        roleRequests.add(new RoleRequest(new Long(3), ERole.ROLE_USER));
        UserRequest userRequest=new UserRequest();
        userRequest.setRoles(roleRequests);
        userRequest.setName(signupRequest.getName());
        userRequest.setEmail(signupRequest.getEmail());
        userRequest.setPassword(signupRequest.getPassword());
        userRequest.setGender(signupRequest.getGender());
        userRequest.setAddr(signupRequest.getAddr());
        userRequest.setBirthday(signupRequest.getBirthday());
        userRequest.setPhone(signupRequest.getPhone());
        userRequest.setNoneLocked(false);
        userService.registerAccount(token,userRequest);
    }

}
