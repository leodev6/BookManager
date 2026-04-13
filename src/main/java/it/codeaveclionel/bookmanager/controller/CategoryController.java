package it.codeaveclionel.bookmanager.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.codeaveclionel.bookmanager.dto.response.CategoryResponse;
import it.codeaveclionel.bookmanager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.processing.SupportedOptions;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Gestion des categories")
public class CategoryController {

    private final CategoryService categoryService;

// ---------------------------------------------------------------------
// GET /api/categories -> liste de categories
// ---------------------------------------------------------------------
    @GetMapping
    @Operation(summary = "Récupérer toutes les categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
