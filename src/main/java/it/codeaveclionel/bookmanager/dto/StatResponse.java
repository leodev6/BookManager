package it.codeaveclionel.bookmanager.dto;

public record StatResponse(
        long totalBooks,
        long readBooks,
        long unreadBooks
) {
}
