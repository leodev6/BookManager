package it.codeaveclionel.bookmanager.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Le nom de la categorie est obligatoire")
        String name,

        String description
) {

}
