package com.example.managementlibrary.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {
    private Long id;
    private List<CartItemResponse> cartItems;
}
