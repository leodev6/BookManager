package it.codeaveclionel.bookmanager.service;


import it.codeaveclionel.bookmanager.dto.BookRequest;
import it.codeaveclionel.bookmanager.dto.BookResponse;
import it.codeaveclionel.bookmanager.dto.StatResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<BookResponse> getAllBooks(Pageable pageable);
    BookResponse getBookById(Long id);
    BookResponse createBook(BookRequest request);
    BookResponse ipdateBook(Long id, BookRequest request);
    void deleteBook(Long id);
    BookResponse toogleReadStatus(Long id);
    Page<BookResponse> searchByTitle(String title, Pageable pageable);
    Page<BookResponse> filterBooks(String category, boolean isRead, Pageable pageable);
    StatResponse getStats();

}
