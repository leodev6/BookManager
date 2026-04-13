package it.codeaveclionel.bookmanager.serviceImpl;

import it.codeaveclionel.bookmanager.dto.request.CategoryRequest;
import it.codeaveclionel.bookmanager.dto.response.CategoryResponse;
import it.codeaveclionel.bookmanager.entity.Category;
import it.codeaveclionel.bookmanager.exeption.ConflictExeption;
import it.codeaveclionel.bookmanager.exeption.ResourceNotFoundExeption;
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
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Catégorie", id));
        return categoryMapper.toResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new ConflictExeption("Catégorie déjà existante : " + request.name());
        }
        Category saved = categoryRepository.save(categoryMapper.toEntity(request));
        return categoryMapper.toResponse(saved);
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
