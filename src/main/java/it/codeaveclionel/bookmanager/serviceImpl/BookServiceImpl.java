package it.codeaveclionel.bookmanager.serviceImpl;

import it.codeaveclionel.bookmanager.dto.request.BookRequest;
import it.codeaveclionel.bookmanager.dto.response.BookResponse;
import it.codeaveclionel.bookmanager.dto.response.StatResponse;
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
    @Transactional
    public BookResponse createBook(BookRequest request) {
        Book book = bookMapper.toEntity(request);

        //Si marqué lu dès la création et pas de dataRead -> on la renseigne automatiquement
        if (book.isRead() && book.getDate_read() == null) {
            book.setDate_read(LocalDate.now());
        }
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = findBookOrThrow(id);
        bookMapper.updateEntity(book, request);

        // Cohérence : si non lu, on efface la dateRead
        if (!book.isRead()) {
            book.setDate_read(null);
        }
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundExeption("Livre", id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookResponse toogleReadStatus(Long id) {
        Book book = findBookOrThrow(id);
        boolean newStatus = !book.isRead();
        book.setRead(newStatus);
        if (newStatus) {
            book.setDate_read(LocalDate.now());
        } else {
            book.setDate_read(null);
        }
        return bookMapper.toResponse(bookRepository.save(book));
    }

    @Override
    public Page<BookResponse> searchByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable)
                .map(bookMapper::toResponse);
    }

    @Override
    public Page<BookResponse> filterBooks(String category, Boolean isRead, Pageable pageable) {
        if (category != null && isRead != null) {
            return bookRepository.findByCategoryAndIsRead(category, isRead, pageable)
                    .map(bookMapper::toResponse);
        } else if (category != null) {
            return bookRepository.findByCategory(category, pageable)
                    .map(bookMapper::toResponse);
        } else if (isRead != null) {
            return bookRepository.findByIsRead(isRead, pageable)
                    .map(bookMapper::toResponse);

        } else {
            return bookRepository.findAll(pageable).map(bookMapper::toResponse);
        }

    }

    @Override
    public StatResponse getStats() {
        return null;
    }
}
