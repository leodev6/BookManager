package it.codeaveclionel.bookmanager.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookRequest(
        @NotBlank(message = "Le titre est obligatoire")
        @Size(max = 255, message = "Le titre ne peux pas dépasser 255 caractères")
        @Column(nullable = false)
        String title,

        @NotBlank(message = "L'auteur' est obligatoire")
        @Size(max = 255, message = "L'auteur' ne peux pas dépasser 255 caractères")
        String author,

        @Size(max = 255, message = "La description ne peux pas dépasser 255 caractères")
        String description,

        @NotBlank(message = "La categorie est obligatoire")
        String category,

        @Column(nullable = false, columnDefinition = "boolean default false")
        boolean isRead,

        @PastOrPresent(message = "La date de lecture ne peut pas etre dans le future")
        LocalDate dateRead
) {
}
