package it.codeaveclionel.bookmanager.dto;


import java.time.LocalDate;

public record BookResponse(
        Long id,
        String title,
        String author,
        String description,
        String category,
        boolean isRead,
        LocalDate date_added,
        LocalDate date_read
) {
}
