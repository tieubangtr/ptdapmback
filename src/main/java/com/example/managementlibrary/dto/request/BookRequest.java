package com.example.managementlibrary.dto.request;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String image;
    @Min(value = 1)
    @NotNull
    private Long count;

    @NotNull
    private Long publishAt;

    private String content;
    @Min(value = 1)
    @NotNull
    private Long authorId;
    @Min(value = 1)
    @NotNull
    private Long categoryId;
    @Min(value = 1)
    @NotNull
    private Long publisherId;
}
