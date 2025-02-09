package kz.net.book_management.service.impl;

import kz.net.book_management.model.dto.LoanDto;
import kz.net.book_management.model.dto.LoanStatusDto;
import kz.net.book_management.model.dto.MemberDto;
import kz.net.book_management.model.entity.Book;
import kz.net.book_management.model.entity.BookLoan;
import kz.net.book_management.model.entity.LibraryMember;
import kz.net.book_management.repository.LibraryMemberRepository;
import kz.net.book_management.service.BookLoanService;
import kz.net.book_management.service.BookService;
import kz.net.book_management.service.LibraryMemberService;
import kz.net.book_management.service.kafka.impl.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class LibraryMemberServiceImpl implements LibraryMemberService {

    @Autowired
    private LibraryMemberRepository libraryMemberRepository;

    @Autowired
    private BookLoanService bookLoanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ProducerService producerService;

    @Override
    public List<MemberDto> getAll() {

        log.trace("bHg7pXgmhy :: Get all library members");

        return libraryMemberRepository.findAll()
                .stream()
                .map((libraryMember -> {
                    MemberDto memberDto = new MemberDto();
                    memberDto.id = libraryMember.getId().toString();
                    memberDto.fullName = libraryMember.getFullName();
                    memberDto.email = libraryMember.getEmail();
                    memberDto.phoneNumber = libraryMember.getPhoneNumber();
                    memberDto.address = libraryMember.getAddress();
                    memberDto.createdDate = libraryMember.getCreatedAt();
                    return memberDto;
                }))
                .toList();

    }

    @Override
    public LibraryMember save(MemberDto member) {

        LibraryMember libraryMember = new LibraryMember();

        if (member.id != null) {

            libraryMember = libraryMemberRepository.findById(UUID.fromString(member.id))
                    .orElseThrow(() -> new RuntimeException("RnxLs9Ir :: libraryMember with id " + member.id + " not found"));

            log.info("y5ZnBvucYV :: will create new book");

        } else {
            libraryMember.setEmail(member.email);
        }

        libraryMember.setAddress(member.address);
        libraryMember.setPhoneNumber(member.phoneNumber);
        libraryMember.setFullName(member.fullName);

        log.info("6oXLYLu :: going to save libraryMember : {}", libraryMember);

        LibraryMember m = libraryMemberRepository.save(libraryMember);

        producerService.auditHistory(String.format("5mG6kl8 :: member with id %s was saved, member: %s ", libraryMember.getId(), libraryMember));

        return m;

    }

    @Override
    public void deleteMember(String id) {

        Optional<LibraryMember> authorOpt = libraryMemberRepository.findById(UUID.fromString(id));

        LibraryMember libraryMember = authorOpt.orElseThrow(
                () -> new RuntimeException("qJQtZ94aVM3 :: member with id " + id + " not found"));

        log.info("YpWZvGM1F :: member: {} will be deleted", libraryMember);

        libraryMemberRepository.delete(libraryMember);

        log.info("U2LBJjPJH :: member with id {} was deleted", id);

        producerService.auditHistory(String.format("Deleted library member with id %s", id));

    }

    @Override
    public LibraryMember getMember(String id) {

        log.debug("EvwiKqJTk :: getMember id: {}", id);

        return libraryMemberRepository.findById(UUID.fromString(id))
                .orElse(null);

    }

    @Override
    public List<LoanDto> getAllLoans() {

        return bookLoanService.getAllLoans();

    }

    @Override
    public BookLoan loanBook(LoanStatusDto loan) {

        String bookId = loan.bookId;

        Book book = bookService.getBook(bookId);

        if (book == null) throw new RuntimeException("qQPac9lR :: book with id: " + bookId + " not found");

        LibraryMember member = getMember(loan.memberId);

        if (member == null) throw new RuntimeException("DYsy9gVi :: member with id: " + loan.memberId + " not found");

        return bookLoanService.createBookLoanRecord(book, member);

    }

    @Override
    public BookLoan setLoanReturn(LoanStatusDto loan) {

        log.trace("wb9cKKhpfaL :: setLoanReturn");

        return bookLoanService.setReturnDate(loan.loanId, loan.loanReturnDate);

    }


}
