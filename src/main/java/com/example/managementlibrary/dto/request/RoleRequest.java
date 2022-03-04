package com.example.managementlibrary.dto.request;

import com.example.managementlibrary.common.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    @Min(value = 1)
    @Min(value = 3)
    private Long id;
    @NotBlank
    private ERole name;

}
