package kz.net.book_management.service;

import jakarta.validation.constraints.NotBlank;
import kz.net.book_management.model.dto.LoanDto;
import kz.net.book_management.model.entity.Book;
import kz.net.book_management.model.entity.BookLoan;
import kz.net.book_management.model.entity.LibraryMember;

import java.time.Instant;
import java.util.List;

public interface BookLoanService {

    BookLoan createBookLoanRecord(Book book, LibraryMember libraryMember);

    BookLoan setReturnDate(@NotBlank String loanId, @NotBlank Instant returnDate);

    List<LoanDto> getAllLoans();

}
