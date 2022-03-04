package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.CommentRequest;
import com.example.managementlibrary.dto.response.CommentResponse;
import com.example.managementlibrary.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "request.userId",target = "user.id")
    @Mapping(source = "request.bookId",target = "book.id")
    @Mapping(source = "request.parentId",target = "parent.id")
    Comment dtoToEntity(CommentRequest request);
    CommentResponse entityToDto(Comment comment);
}
