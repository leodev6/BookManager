package it.codeaveclionel.bookmanager.serviceImpl;

import it.codeaveclionel.bookmanager.dto.request.CategoryRequest;
import it.codeaveclionel.bookmanager.dto.response.CategoryResponse;
import it.codeaveclionel.bookmanager.mapper.CategoryMapper;
import it.codeaveclionel.bookmanager.repository.CategoryRepository;
import it.codeaveclionel.bookmanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return null;
    }

    @Override
    public CategoryResponse CreateCategory(CategoryRequest request) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
