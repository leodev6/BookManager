package it.codeaveclionel.bookmanager.mapper;

import it.codeaveclionel.bookmanager.entity.Category;
import it.codeaveclionel.bookmanager.request.CategoryRequest;
import it.codeaveclionel.bookmanager.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public Category toEntity(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }
}
