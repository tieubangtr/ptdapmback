package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.CartRequest;
import com.example.managementlibrary.dto.response.CartResponse;
import com.example.managementlibrary.entity.Cart;
import com.example.managementlibrary.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/carts")
public class CartController extends GenericAPI<Cart,Long, CartRequest, CartResponse> {
    public CartController(GenericService<Cart, Long, CartRequest, CartResponse> genericService) {
        super(genericService);
    }
}
