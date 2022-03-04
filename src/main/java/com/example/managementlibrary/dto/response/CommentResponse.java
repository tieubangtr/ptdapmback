package com.example.managementlibrary.dto.response;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class CommentResponse {
    private Long id;
    private UserResponse user;
    private BookResponse book;
    private LocalDateTime createdAt;
    private List<CommentResponse> subComments;
    private String content;
}
