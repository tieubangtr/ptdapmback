package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.CartItemRequest;
import com.example.managementlibrary.dto.response.CartItemResponse;
import com.example.managementlibrary.entity.Cart;
import com.example.managementlibrary.entity.CartItem;
import com.example.managementlibrary.exception.GenericException;
import com.example.managementlibrary.mapper.CartItemMapper;
import com.example.managementlibrary.repository.CartRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.CartItemService;
import com.example.managementlibrary.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl extends GenericServiceImp<CartItem,Long, CartItemRequest, CartItemResponse> implements CartItemService {
    public CartItemServiceImpl(GenericRepository<CartItem, Long> repository) {
        super(repository);
    }

    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    CartRepository cartRepository;


    @Override
    public CartItem transformDTOToEntity(CartItemRequest element) {
        return cartItemMapper.dtoToEntity(element);
    }

    @Override
    public CartItemResponse transformEntityToDTO(CartItem element) {
        return cartItemMapper.entityToDto(element);
    }

}
