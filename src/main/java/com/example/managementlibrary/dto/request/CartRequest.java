package com.example.managementlibrary.dto.request;

import com.example.managementlibrary.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CartRequest {

    @NotNull
    @Min(value = 1)
    private Long id;
    @NotNull
    @Min(value = 1)
    private Integer total;
}
