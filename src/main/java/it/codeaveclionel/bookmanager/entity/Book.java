package it.codeaveclionel.bookmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    @Size(max = 255, message = "Le titre ne peux pas dépasser 255 caractères")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "L'auteur' est obligatoire")
    @Size(max = 255, message = "L'auteur' ne peux pas dépasser 255 caractères")
    @Column(nullable = false)
    private String author;

    @Size(max = 255, message = "La description ne peux pas dépasser 255 caractères")
    @Column(length = 500)
    private String description;

    @NotBlank(message = "La categorie est obligatoire")
    @Column(nullable = false)
    private String category;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isRead;

    @Column(name = "date_added", nullable = false, updatable = false)
    private LocalDate dateAdded;

    @PastOrPresent(message = "La date de lecture ne peut pas etre dans le future")
    private LocalDate date_read;

    @PrePersist
    protected void onCreate() {
        this.dateAdded = LocalDate.now();
    }
}
