package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.CartRequest;
import com.example.managementlibrary.dto.response.CartResponse;
import com.example.managementlibrary.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart dtoToEntity(CartRequest request);
    CartResponse entityToDto(Cart cart);
}
