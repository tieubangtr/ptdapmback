package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.entity.RefreshToken;
import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.repository.RefreshTokenRepository;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${nqh.app.jwtExpirationMs}")
    private Long refreshTokenDurationMs;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<RefreshToken>findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(userId).orElse(new RefreshToken());
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new GenericException("Refresh token was expired. Please make a new login request");
        }
        return token;
    }



    @Override
    public void deleteRefreshToken(Long userId) {
        if( refreshTokenRepository.existsById(userId)){
            refreshTokenRepository.deleteById(userId);
        }

    }


}
