package it.codeaveclionel.bookmanager.mapper;

import it.codeaveclionel.bookmanager.entity.Book;
import it.codeaveclionel.bookmanager.dto.BookRequest;
import it.codeaveclionel.bookmanager.dto.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getCategory(),
                book.isRead(),
                book.getDateAdded(),
                book.getDateRead()
        );
    }

    public Book toEntity(BookRequest request) {
        return Book.builder()
                .title(request.title())
                .author(request.author())
                .category(request.category())
                .isRead(request.isRead())
                .dateRead(request.dateRead())
                .description(request.description())
                .build();
    }

    public void updateEntity(Book book, BookRequest request) {
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setDescription(request.description());
        book.setCategory(request.category());
        book.setRead(request.isRead());
        book.setDateRead(request.dateRead());
    }
}
