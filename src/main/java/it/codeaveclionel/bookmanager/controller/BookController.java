package it.codeaveclionel.bookmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.codeaveclionel.bookmanager.dto.BookRequest;
import it.codeaveclionel.bookmanager.dto.BookResponse;
import it.codeaveclionel.bookmanager.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "Gestion des livres")
public class BookController {

    private final BookService bookService;

// ---------------------------------------------------------------------
// GET /api/books -> liste paginée de livre
// ---------------------------------------------------------------------
    @GetMapping
    @Operation(summary = "Récupérer tous les livres (paginé)")
    public ResponseEntity<Page<BookResponse>> getAllBooks(
            @PageableDefault(size = 10, sort = "dataAdded", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

// ---------------------------------------------------------------------
// GET /api/books/{id} -> recupère un livre
// ---------------------------------------------------------------------
    @GetMapping("/{id}")
    @Operation(summary = "Récupèrer un livre par son identifiant")
    public ResponseEntity<BookResponse> getBookById(
            @Parameter(description = "Identifiant du livre") @PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }


// ---------------------------------------------------------------------
// GET /api/books -> créer un nouveau un livre
// ---------------------------------------------------------------------
    @PostMapping
    @Operation(summary = "Créer un nouveau un livre")
    public ResponseEntity<BookResponse> createBook( @Valid @RequestBody BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.createBook(request));
    }

}
