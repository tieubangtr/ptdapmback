package com.example.managementlibrary.dto.request;


import com.example.managementlibrary.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest  {

    @NotBlank
    private String name;
}
