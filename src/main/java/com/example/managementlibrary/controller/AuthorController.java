package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.AuthorRequest;
import com.example.managementlibrary.dto.response.AuthorResponse;
import com.example.managementlibrary.entity.Author;
import com.example.managementlibrary.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/authors")
public class AuthorController extends GenericAPI<Author,Long, AuthorRequest, AuthorResponse>{
    public AuthorController(GenericService<Author, Long, AuthorRequest, AuthorResponse> genericService) {
        super(genericService);
    }
}
