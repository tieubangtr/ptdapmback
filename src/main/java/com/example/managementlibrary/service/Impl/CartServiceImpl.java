package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.CartRequest;
import com.example.managementlibrary.dto.response.CartResponse;
import com.example.managementlibrary.entity.Cart;
import com.example.managementlibrary.mapper.CartMapper;
import com.example.managementlibrary.repository.CartRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends GenericServiceImp<Cart,Long, CartRequest, CartResponse> implements CartService {
    public CartServiceImpl(GenericRepository<Cart, Long> repository) {
        super(repository);
    }


    @Autowired
    CartMapper cartMapper;

    @Override
    public Cart transformDTOToEntity(CartRequest element) {
        return cartMapper.dtoToEntity(element);
    }

    @Override
    public CartResponse transformEntityToDTO(Cart element) {
        return cartMapper.entityToDto(element);
    }
}
