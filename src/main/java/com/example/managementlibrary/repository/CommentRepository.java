package com.example.managementlibrary.repository;

import com.example.managementlibrary.entity.Comment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends GenericRepository<Comment,Long>{
    @Query("from Comment as c where c.parent is null and c.book.id = ?1")
    List<Comment> findByParentAndBookId(Long bookId);
}
