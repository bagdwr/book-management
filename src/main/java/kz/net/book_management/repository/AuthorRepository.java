package kz.net.book_management.repository;

import kz.net.book_management.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    Optional<Author> findAuthorById(UUID id);

    Optional<Author> findAuthorByEmail(String email);

}
