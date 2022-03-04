package com.example.managementlibrary.mapper;

import com.example.managementlibrary.dto.request.CategoryRequest;
import com.example.managementlibrary.dto.response.CategoryResponse;
import com.example.managementlibrary.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToEntity(CategoryRequest categoryRequest);
    CategoryResponse entityToDto(Category category);
}
