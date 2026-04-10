package it.codeaveclionel.bookmanager.response;

public record StatResponse(
        long totalBooks,
        long readBooks,
        long unreadBooks
) {
}
