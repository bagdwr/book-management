package kz.net.book_management.service;

import jakarta.validation.constraints.NotBlank;
import kz.net.book_management.model.dto.MemberDto;
import kz.net.book_management.model.entity.LibraryMember;

import java.util.List;

public interface LibraryMemberService {

    List<MemberDto> getAll();

    LibraryMember save(MemberDto member);

    void deleteMember(String id);

    LibraryMember getMember(@NotBlank String id);

}
