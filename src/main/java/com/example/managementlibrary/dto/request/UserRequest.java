package com.example.managementlibrary.dto.request;

import com.example.managementlibrary.dto.BaseDto;
import lombok.Data;
import lombok.ToString;
import lombok.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@ToString
public class UserRequest {


    @NotBlank
    private String name;
    @Email
    private String email;
    @Size(min = 6,max = 10)
    private String password;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;
    @NotBlank
    private String gender;
    @NotBlank
    private String addr;
    private String img;
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;
    private boolean isNoneLocked=true;

    @NotNull
    private Set<RoleRequest> roles;


}
