package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.CartItemRequest;
import com.example.managementlibrary.dto.response.CartItemResponse;
import com.example.managementlibrary.entity.Cart;
import com.example.managementlibrary.entity.CartItem;
import com.example.managementlibrary.service.GenericService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/cartItems")
public class CartItemController extends GenericAPI<CartItem,Long, CartItemRequest, CartItemResponse> {
    public CartItemController(GenericService<CartItem, Long, CartItemRequest, CartItemResponse> genericService) {
        super(genericService);
    }
}
