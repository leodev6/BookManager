package it.codeaveclionel.bookmanager.serviceImpl;

import it.codeaveclionel.bookmanager.dto.BookRequest;
import it.codeaveclionel.bookmanager.dto.BookResponse;
import it.codeaveclionel.bookmanager.dto.StatResponse;
import it.codeaveclionel.bookmanager.entity.Book;
import it.codeaveclionel.bookmanager.exeption.ResourceNotFoundExeption;
import it.codeaveclionel.bookmanager.mapper.BookMapper;
import it.codeaveclionel.bookmanager.repository.BookRepository;
import it.codeaveclionel.bookmanager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.ReadOnlyFileSystemException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional()
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Page<BookResponse> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toResponse);
    }

    @Override
    public BookResponse getBookById(Long id) {
        return bookMapper.toResponse(findBookOrThrow(id));
    }

    // -------- Helpers ---------
    private Book findBookOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeption("Livre", id));
    }

    @Override
    public BookResponse createBook(BookRequest request) {
        Book book = bookMapper.toEntity(request);

        //Si marqué lu dès la création et pas de dataRead -> on la renseigne automatiquement
        if (book.isRead() && book.getDate_read() == null) {
            book.setDate_read(LocalDate.now());
        }
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    public BookResponse ipdateBook(Long id, BookRequest request) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public BookResponse toogleReadStatus(Long id) {
        return null;
    }

    @Override
    public Page<BookResponse> searchByTitle(String title, Pageable pageable) {
        return null;
    }

    @Override
    public Page<BookResponse> filterBooks(String category, boolean isRead, Pageable pageable) {
        return null;
    }

    @Override
    public StatResponse getStats() {
        return null;
    }
}
