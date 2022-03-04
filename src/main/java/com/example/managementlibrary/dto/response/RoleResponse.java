package com.example.managementlibrary.dto.response;

import com.example.managementlibrary.common.ERole;
import lombok.Data;

@Data
public class RoleResponse {
    private Long id;
    private ERole name;
}
