package com.example.managementlibrary.controller;

import com.example.managementlibrary.Util.Utility;
import com.example.managementlibrary.dto.request.LoginRequest;
import com.example.managementlibrary.dto.request.SignupRequest;
import com.example.managementlibrary.dto.request.TokenRefreshRequest;
import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.LoginResponse;
import com.example.managementlibrary.dto.response.TokenRefreshResponse;
import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.service.AuthService;
import com.example.managementlibrary.service.MailService;
import com.example.managementlibrary.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@Validated @RequestBody LoginRequest loginRequest) {
        try {
            return new ResponseEntity(authService.userLogin(loginRequest), HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Login failed", e), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Validated @RequestBody SignupRequest signupRequest, HttpServletRequest request) {
        try {
            String token = RandomString.make(30);
            authService.userSignup(signupRequest, token);
            String verityLink = Utility.getSiteURL(request) + "/v1/api/auth/verify?token=" + token;
            mailService.sendEmailVerityAccount(signupRequest.getEmail(), verityLink);

            return new ResponseEntity("Please check your mail to verify your account", HttpStatus.OK);
        } catch (MessagingException | GenericException e) {
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Register failed", e), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
        try {
            userService.verify(token);
            return new ResponseEntity("Verify successfully", HttpStatus.OK);
        } catch (GenericException e) {
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "Verify failed", e), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Validated @RequestBody TokenRefreshRequest tokenRefreshRequest) {
        try {
            return new ResponseEntity(authService.refreshToken(tokenRefreshRequest), HttpStatus.OK);
        } catch (GenericException e) {
            return new ResponseEntity(new ApiError(HttpStatus.CONFLICT, "refresh failed", e), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> processForgotPassword(@RequestParam("email") String email, HttpServletRequest request) throws MessagingException {

        String token = RandomString.make(30);
        String message = "";
        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/v1/api/users/reset_password?token=" + token;
            mailService.sendEmailResetPassword(email, resetPasswordLink);
            message = token;
        } catch (GenericException e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, "Change password failed", e), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/reset_password")
    public ResponseEntity<?> processResetPassword(
            @RequestParam("token") String token,
            @RequestParam("newPassword") String newPassword) {

        String message = "";
        try {
            userService.updatePassword(token, newPassword);
            message = "Change password successfully";
        }
        catch (GenericException e){
            return new ResponseEntity<>(new ApiError(HttpStatus.CONFLICT, "Change password failed", e),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
