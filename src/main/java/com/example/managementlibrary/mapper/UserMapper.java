package com.example.managementlibrary.mapper;


import com.example.managementlibrary.dto.request.UserRequest;
import com.example.managementlibrary.dto.response.UserResponse;
import com.example.managementlibrary.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserRequest userRequest);
    UserResponse entityToDTO(User user);
}
