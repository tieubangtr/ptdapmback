package com.example.managementlibrary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {


    private Long id;
    private String username;
    private List<String> roles;
    private String type = "Bearer";
    private String token;
    private String refreshToken;

    public LoginResponse(Long id, String username, List<String> roles, String token, String refreshToken) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
