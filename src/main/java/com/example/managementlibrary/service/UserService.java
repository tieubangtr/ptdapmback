package com.example.managementlibrary.service;

import com.example.managementlibrary.common.Filter;
import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends GenericService<User,Long, UserRequest, UserResponse> {

    void updateResetPasswordToken(String token, String email);
    void updatePassword(String token, String newPassword);
    void registerAccount(String token,UserRequest userRequest);
    void verify(String token);
    void updatePassword(Long userId, String newPassword, String oldPassword);
    void updateImage(Long id,String img);

}
