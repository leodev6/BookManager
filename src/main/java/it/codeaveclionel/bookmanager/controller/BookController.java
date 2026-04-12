package it.codeaveclionel.bookmanager.controller;

import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.codeaveclionel.bookmanager.dto.request.BookRequest;
import it.codeaveclionel.bookmanager.dto.response.BookResponse;
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
            @PageableDefault(size = 10, sort = "dateAdded", direction = Sort.Direction.DESC)
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

// ---------------------------------------------------------------------
// GET /api/books/{id} -> modifier un livre
// ---------------------------------------------------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un livre existant")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

// ---------------------------------------------------------------------
// GET /api/books/{id} -> supprimer un livre
// ---------------------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un livre")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

// ------------------------------------------------------------------------
// GET /api/books/{id}/read -> toggle pour changer le statut (lu / non lu)
// ------------------------------------------------------------------------
    @PatchMapping("/{id}/read")
    @Operation(summary = "Basculer le statut de lecture d'un livre")
    public ResponseEntity<BookResponse> toogleReadStatus(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.toogleReadStatus(id));
    }

// ------------------------------------------------------------------------
// GET /api/books/search?title= -> rechercher un livre par son titre
// ------------------------------------------------------------------------
    @GetMapping("/search")
    @Operation(summary = "Rechercher des livres par titre")
    public ResponseEntity<Page<BookResponse>> searchByTitle(@RequestParam String title,
                                                            @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        return ResponseEntity.ok(bookService.searchByTitle(title, pageable));
    }

}
