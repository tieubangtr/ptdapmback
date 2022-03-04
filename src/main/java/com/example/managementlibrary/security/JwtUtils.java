package com.example.managementlibrary.security;


import com.example.managementlibrary.entity.User;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.repository.RefreshTokenRepository;
import com.example.managementlibrary.repository.UserRepository;
import com.example.managementlibrary.service.RefreshTokenService;
import com.example.managementlibrary.service.UserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${nqh.app.jwtSecret}")
    private String jwtSecret;
    @Value("${nqh.app.jwtExpirationMs}")
    private String jwtExpirationMs;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;



    public String generateJwtToken(Authentication authentication){
        MyUser userDetails = (MyUser) authentication.getPrincipal();
        long time=System.currentTimeMillis()+Long.valueOf(jwtExpirationMs);
        Date date=new Date();
        Date dateExpiration=new Date(time);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(date)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }


    public String generateTokenFromUsername(String username) {
        long time=System.currentTimeMillis()+Long.valueOf(jwtExpirationMs);
        Date dateExpiration=new Date(time);
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(dateExpiration).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            User user=userRepository.findByEmail(getUserNameFromJwtToken(authToken));
            if(user!=null){
                return refreshTokenRepository.existsById(user.getId());
            }
            return false;

        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw new GenericException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new GenericException("Expired JWT token");

        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new GenericException("Unsupported JWT token");

        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw new GenericException("JWT claims string is empty.");

        }
    }
}
