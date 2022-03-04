package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.AuthorRequest;
import com.example.managementlibrary.dto.response.AuthorResponse;
import com.example.managementlibrary.entity.Author;

public interface AuthorService extends GenericService<Author,Long, AuthorRequest, AuthorResponse> {
}
