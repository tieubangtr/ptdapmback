package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.CartItemRequest;
import com.example.managementlibrary.dto.response.CartItemResponse;
import com.example.managementlibrary.entity.CartItem;

public interface CartItemService extends GenericService<CartItem,Long, CartItemRequest, CartItemResponse>{
}
