package com.example.managementlibrary.controller;

import com.example.managementlibrary.dto.request.CategoryRequest;
import com.example.managementlibrary.dto.response.CategoryResponse;
import com.example.managementlibrary.entity.Category;
import com.example.managementlibrary.service.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/categories")
public class CategoryController extends GenericAPI<Category,Long, CategoryRequest, CategoryResponse> {
    public CategoryController(GenericService<Category, Long, CategoryRequest, CategoryResponse> genericService) {
        super(genericService);
    }
}
