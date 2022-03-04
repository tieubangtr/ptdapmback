package com.example.managementlibrary.dto.request;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;
import java.util.Set;

@Data
@ToString
public class SignupRequest {
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
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;


}
