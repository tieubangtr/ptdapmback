package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.common.Filter;
import com.example.managementlibrary.dto.request.CommentRequest;
import com.example.managementlibrary.dto.response.CommentResponse;
import com.example.managementlibrary.entity.Comment;
import com.example.managementlibrary.mapper.CommentMapper;
import com.example.managementlibrary.repository.CommentRepository;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends GenericServiceImp<Comment,Long, CommentRequest, CommentResponse> implements CommentService {
    public CommentServiceImpl(GenericRepository<Comment, Long> repository) {
        super(repository);
    }

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentRepository commentRepository;
    @Override
    public Comment transformDTOToEntity(CommentRequest element) {
        Comment comment=commentMapper.dtoToEntity(element);
        if(element.getParentId()==null||element.getParentId()==0){
            comment.setParent(null);
        }
        return comment;
    }

    @Override
    public Page<CommentResponse> getPage(int page, int limit, String sort, List<Filter> filters) {
        return super.getPage(page, limit, sort, filters);
    }


    @Override
    public CommentResponse transformEntityToDTO(Comment element) {
        return commentMapper.entityToDto(element);
    }

    @Override
    public List<CommentResponse> findByParentExistsAndBookId(Long bookId) {
        return commentRepository.findByParentAndBookId(bookId).stream().map(e->transformEntityToDTO(e)).collect(Collectors.toList());
    }
}
