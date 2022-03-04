package com.example.managementlibrary.dto.response;

import com.example.managementlibrary.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse extends BaseDto {

    private String name;
}
