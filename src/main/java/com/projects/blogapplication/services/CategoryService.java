package com.projects.blogapplication.services;

import com.projects.blogapplication.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);
    CategoryDTO getCategory(Integer categoryId);
    List<CategoryDTO> getAllCategories();
    void deleteCategory(Integer categoryId);
}
