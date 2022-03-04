package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.AuthorRequest;
import com.example.managementlibrary.dto.response.AuthorResponse;
import com.example.managementlibrary.entity.Author;
import com.example.managementlibrary.mapper.AuthorMapper;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl extends GenericServiceImp<Author,Long, AuthorRequest, AuthorResponse> implements AuthorService {
    public AuthorServiceImpl(GenericRepository<Author, Long> repository) {
        super(repository);
    }

    @Autowired
    AuthorMapper authorMapper;
    @Override
    public Author transformDTOToEntity(AuthorRequest element) {
        return authorMapper.dtoToEntity(element);
    }

    @Override
    public AuthorResponse transformEntityToDTO(Author element) {
        return authorMapper.entityToDTO(element);
    }
}
