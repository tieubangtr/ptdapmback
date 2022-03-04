package com.example.managementlibrary.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CartItemRequest {

    @NotNull
    @Min(value = 1)
    private Long cartId;
    @NotNull
    @Min(value = 1)
    private Long bookId;
}
