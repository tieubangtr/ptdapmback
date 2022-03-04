package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.CartRequest;
import com.example.managementlibrary.dto.response.CartResponse;
import com.example.managementlibrary.entity.Cart;

public interface CartService extends GenericService<Cart,Long, CartRequest, CartResponse>{
}
