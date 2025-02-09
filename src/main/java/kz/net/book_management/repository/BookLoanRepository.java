package kz.net.book_management.repository;

import kz.net.book_management.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, UUID> {
}
