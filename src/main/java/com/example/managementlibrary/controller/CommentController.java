package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.CommentRequest;
import com.example.managementlibrary.dto.response.CommentResponse;
import com.example.managementlibrary.entity.Comment;
import com.example.managementlibrary.service.CommentService;
import com.example.managementlibrary.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/comments")
public class CommentController extends GenericAPI<Comment,Long, CommentRequest, CommentResponse> {
    public CommentController(GenericService<Comment, Long, CommentRequest, CommentResponse> genericService) {
        super(genericService);
    }
    @Autowired
    CommentService commentService;

    @GetMapping("/book/{id}")
    public ResponseEntity<List<CommentResponse>> findByParentExistsAndBookId(@PathVariable Long id){
        return new ResponseEntity<>( commentService.findByParentExistsAndBookId(id), HttpStatus.OK);
    }
}
