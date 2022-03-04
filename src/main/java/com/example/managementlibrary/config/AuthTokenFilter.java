package com.example.managementlibrary.config;



import com.example.managementlibrary.exception.ApiError;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.security.CustomUserDetailsService;
import com.example.managementlibrary.security.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class AuthTokenFilter extends OncePerRequestFilter {


    @Autowired
    JwtUtils jwtUtils;



    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt=parseJwt(request);
            if(jwt!=null && jwtUtils.validateJwtToken(jwt)){
                String username=jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails=userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (GenericException | UsernameNotFoundException e){
            System.out.println(e);
            request.setAttribute("error",e.getMessage());
        }



        filterChain.doFilter(request,response);
    }
    private String getCookieValue(HttpServletRequest req, String cookieName) {
        if(req.getCookies()!=null){
            return Arrays.stream(req.getCookies())
                    .filter(c -> c.getName().equals(cookieName))
                    .findFirst()
                    .map(Cookie::getValue).get();
        }
        return null;

    }
    private String parseJwt(HttpServletRequest request) {
        String headerAuth=request.getHeader("Authorization");
        if(headerAuth!=null) {
            if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer")){
                headerAuth= headerAuth.substring(7);
            }
        }
        else{
            Optional<String> optionalBearer=Optional.ofNullable(getCookieValue(request,"accessToken"));
            if(optionalBearer.isPresent()){
                headerAuth=optionalBearer.get();
            }
        }
        return headerAuth;
    }
}
