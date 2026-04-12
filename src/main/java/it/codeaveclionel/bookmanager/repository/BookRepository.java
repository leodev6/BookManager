package it.codeaveclionel.bookmanager.repository;

import it.codeaveclionel.bookmanager.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //Reherche par titre (inensible à la casse (EX = jouer <=> JOUER <=> Jouer)
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Filtre par catégorie
    Page<Book> findByCategory(String category, Pageable pageable);

    // Filtre par statut de lecture
    Page<Book> findByIsRead(boolean isRead, Pageable pageable);

    Page<Book> findByCategoryAndIsRead(String category, boolean isRead, Pageable pageable);

    // Filtre par catégory + recherche titre
    Page<Book> findByTitleContainingIgnoreCaseAndCategory(String title, String category, Pageable pageable);

    // Filtre titre + statut
    Page<Book> findByTitleContainingIgnoreCaseAndIsRead(String title, boolean isRead, Pageable pageable);

    // Filtre combiné titre + catégory + statut
    Page<Book> findByTitleContainingIgnoreCaseAndCategoryAndIsRead(String title, String category, boolean isRead, Pageable pageable);

    // Statistiques
    long countByIsRead(boolean isRead);

}
