package it.codeaveclionel.bookmanager.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.codeaveclionel.bookmanager.dto.request.CategoryRequest;
import it.codeaveclionel.bookmanager.dto.response.CategoryResponse;
import it.codeaveclionel.bookmanager.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

// ---------------------------------------------------------------------------
// GET /api/categories/{id} -> récupérer les categories par son identifiant
// ---------------------------------------------------------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer les categories par son identifiant")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

// ---------------------------------------------------------------------------
// POST /api/categories -> créer categories
// ---------------------------------------------------------------------------
    @PostMapping
    @Operation(summary = "Créer categories")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryService.createCategory(request));
    }
}
