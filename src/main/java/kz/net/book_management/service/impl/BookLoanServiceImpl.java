package kz.net.book_management.service.impl;

import kz.net.book_management.model.dto.LoanDto;
import kz.net.book_management.model.entity.Book;
import kz.net.book_management.model.entity.BookLoan;
import kz.net.book_management.model.entity.LibraryMember;
import kz.net.book_management.repository.BookLoanRepository;
import kz.net.book_management.service.BookLoanService;
import kz.net.book_management.service.kafka.impl.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class BookLoanServiceImpl implements BookLoanService {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private ProducerService producerService;

    @Override
    public BookLoan createBookLoanRecord(Book book, LibraryMember libraryMember) {

        log.trace("bPLVy91hFR :: createBookLoanRecord");

        BookLoan bookLoan = new BookLoan();

        bookLoan.setBook(book);
        bookLoan.setMember(libraryMember);
        bookLoan.setLoanDate(Instant.now());

        BookLoan loan = bookLoanRepository.save(bookLoan);

        log.info("0u1XgD9 :: created a new loan: {}", loan);

        producerService.auditHistory(String.format("New load with id %s was created", loan.getId()));

        return loan;
    }

    @Override
    public BookLoan setReturnDate(String loanId, Instant returnDate) {

        Optional<BookLoan> bookLoanOpt = bookLoanRepository.findById(UUID.fromString(loanId));

        BookLoan bookLoan = bookLoanOpt.orElseThrow(
                () -> new RuntimeException("57vc2tc0W :: Book loan with id " + loanId + " not found")
        );

        bookLoan.setReturnDate(returnDate);

        BookLoan loan = bookLoanRepository.save(bookLoan);

        producerService.auditHistory(String.format("Book loan with id %s was updated, loan: %s", loanId, loan));

        return loan;

    }

    @Override
    public List<LoanDto> getAllLoans() {

        log.trace("4l7bL90 :: getAllLoans");

        return bookLoanRepository.findAll()
                .stream()
                .map(loan -> {
                    LoanDto loanDto = new LoanDto();
                    loanDto.id = loan.getId().toString();
                    loanDto.bookId = loan.getBook().getId().toString();
                    loanDto.bookTitle = loan.getBook().getTitle();
                    loanDto.memberId = loan.getMember().getId().toString();
                    loanDto.memberFullName = loan.getMember().getFullName();
                    loanDto.loanDate = loan.getLoanDate();
                    loanDto.loanReturnDate = loan.getReturnDate();

                    return loanDto;
                })
                .toList();
    }
}
