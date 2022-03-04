package com.example.managementlibrary.service;

import com.example.managementlibrary.dto.request.CategoryRequest;
import com.example.managementlibrary.dto.response.CategoryResponse;
import com.example.managementlibrary.entity.Category;

public interface CategoryService extends GenericService<Category,Long, CategoryRequest, CategoryResponse>{
}
