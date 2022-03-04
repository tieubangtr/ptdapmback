package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.CommentRequest;
import com.example.managementlibrary.dto.response.CommentResponse;
import com.example.managementlibrary.entity.Comment;

import java.util.List;

public interface CommentService extends GenericService<Comment,Long, CommentRequest, CommentResponse>{

    List<CommentResponse> findByParentExistsAndBookId(Long BookId);
}
