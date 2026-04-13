package it.codeaveclionel.bookmanager.service;

import it.codeaveclionel.bookmanager.dto.request.CategoryRequest;
import it.codeaveclionel.bookmanager.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse CreateCategory(CategoryRequest request);
    void deleteCategory(Long id);
}
