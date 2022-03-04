package com.example.managementlibrary.controller;



import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.User;

import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.exception.GenericException;

import com.example.managementlibrary.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/v1/api/users")
public class UserController extends GenericAPI<User, Long, UserRequest, UserResponse> {


    @Autowired
    UserService userService;

    @Autowired
    RefreshTokenService refreshTokenService;

    public UserController(GenericService<User, Long, UserRequest, UserResponse> genericService) {
        super(genericService);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestParam("newPassword") String newPassWord,
                                            @RequestParam("oldPassword") String oldPassWord) {
        try {
            userService.updatePassword(id, newPassWord, oldPassWord);
            return new ResponseEntity<>("Change password successfully", HttpStatus.OK);
        } catch (GenericException e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, "Change password failed", e), HttpStatus.CONFLICT);
        }

    }

    @Autowired
    FilesStorageService storageService;

    @PutMapping("/{id}/image")
    public ResponseEntity<String> changeImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        storageService.save(image);
        userService.updateImage(id, image.getOriginalFilename());

        return new ResponseEntity("Upload your image successfully", HttpStatus.OK);
    }


    @PutMapping("/{id}/logout")
    public ResponseEntity<?> logoutUser(@PathVariable Long id) {

        refreshTokenService.deleteRefreshToken(id);
        return ResponseEntity.ok("User has successfully logged out from the system!");
    }

}
