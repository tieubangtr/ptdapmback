package com.example.managementlibrary.dto.request;

import lombok.Data;
import lombok.ToString;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ToString
public class CommentRequest {

    @NotNull
    @Min(value = 1)
    private Long userId;

    private Long parentId;
    @NotNull
    @Min(value = 1)
    private Long bookId;
    @NotBlank
    private String content;
}
