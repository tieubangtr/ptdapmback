package com.example.managementlibrary.service.Impl;

import com.example.managementlibrary.dto.request.CategoryRequest;
import com.example.managementlibrary.dto.response.CategoryResponse;
import com.example.managementlibrary.entity.Category;
import com.example.managementlibrary.mapper.CategoryMapper;
import com.example.managementlibrary.repository.GenericRepository;
import com.example.managementlibrary.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImp<Category,Long, CategoryRequest, CategoryResponse> implements CategoryService {
    public CategoryServiceImpl(GenericRepository<Category, Long> repository) {
        super(repository);
    }

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Category transformDTOToEntity(CategoryRequest element) {
        return categoryMapper.dtoToEntity(element);
    }

    @Override
    public CategoryResponse transformEntityToDTO(Category element) {
        return categoryMapper.entityToDto(element);
    }
}
