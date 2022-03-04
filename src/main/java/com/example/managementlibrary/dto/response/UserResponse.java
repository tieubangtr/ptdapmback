package com.example.managementlibrary.dto.response;

import com.example.managementlibrary.dto.BaseDto;
import com.example.managementlibrary.dto.request.RoleRequest;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@ToString
public class UserResponse extends BaseDto {


    private String name;

    private String email;

    private String password;


    private Date birthday;

    private String gender;

    private String addr;

    private String phone;

    private boolean isNoneLocked;


    @NotNull
    private Set<RoleRequest> roles;
}
