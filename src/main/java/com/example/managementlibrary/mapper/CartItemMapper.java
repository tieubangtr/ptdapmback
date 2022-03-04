package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.CartItemRequest;
import com.example.managementlibrary.dto.request.CartRequest;
import com.example.managementlibrary.dto.response.CartItemResponse;
import com.example.managementlibrary.dto.response.CartResponse;
import com.example.managementlibrary.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(source = "request.bookId",target = "book.id")
    @Mapping(source = "request.cartId",target = "cart.id")
    CartItem dtoToEntity(CartItemRequest request);
    CartItemResponse entityToDto(CartItem cartItem);
}
