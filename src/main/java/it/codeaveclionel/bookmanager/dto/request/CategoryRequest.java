package it.codeaveclionel.bookmanager.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Le nom de la categorie est obligatoire")
        String name,

        String description
) {

}
