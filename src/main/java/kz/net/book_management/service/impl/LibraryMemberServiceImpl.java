package kz.net.book_management.service.impl;

import kz.net.book_management.model.dto.MemberDto;
import kz.net.book_management.model.entity.LibraryMember;
import kz.net.book_management.repository.BookLoanRepository;
import kz.net.book_management.repository.LibraryMemberRepository;
import kz.net.book_management.service.LibraryMemberService;
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
    private BookLoanRepository bookLoanRepository;

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
        return null;
    }

    @Override
    public void deleteMember(String id) {

    }

    @Override
    public LibraryMember getMember(String id) {

        log.debug("EvwiKqJTk :: getMember id: {}", id);

        Optional<LibraryMember> libraryMember = libraryMemberRepository.findById(UUID.fromString(id));

        return libraryMember.orElse(null);
    }
}
