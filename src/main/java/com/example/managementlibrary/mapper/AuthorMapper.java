package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.AuthorRequest;
import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.AuthorResponse;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.Author;
import com.example.managementlibrary.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author dtoToEntity(AuthorRequest authorRequest);
    AuthorResponse entityToDTO(Author author);
}
