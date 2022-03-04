package com.example.managementlibrary.config;


import com.example.managementlibrary.exception.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {



    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{



        ApiError apiError=new ApiError(HttpStatus.UNAUTHORIZED);
        Optional<Object> optional=Optional.ofNullable(request.getAttribute("error"));
        if(optional.isPresent()){
            apiError.setDebugMessage( optional.get().toString());
        }
        else {
            apiError.setDebugMessage(authException.getMessage());
        }

        apiError.setMessage("Error : Unauthorized");
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = mapper.writeValueAsString(apiError);
        response.setContentType("application/json");
        response.getWriter().write(json);
        response.setStatus(401);
        response.flushBuffer();

    }
}
