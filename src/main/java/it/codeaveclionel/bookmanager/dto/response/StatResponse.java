package it.codeaveclionel.bookmanager.dto.response;

public record StatResponse(
        long totalBooks,
        long readBooks,
        long unreadBooks
) {
}
