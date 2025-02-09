package kz.net.book_management.controller;

import jakarta.validation.Valid;
import kz.net.book_management.model.dto.MemberDto;
import kz.net.book_management.model.entity.LibraryMember;
import kz.net.book_management.service.LibraryMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lib-member")
@Slf4j
public class LibraryMemberController {

    @Autowired
    private LibraryMemberService libraryMemberService;

    @GetMapping("/members")
    public List<MemberDto> getAllMembers() {

        return libraryMemberService.getAll();

    }

    @PostMapping("/member")
    public LibraryMember createMember(@RequestBody @Valid MemberDto member) {

        log.info("2f8syvg :: member creation process: {}", member);

        return libraryMemberService.save(member);

    }

    @DeleteMapping("/member")
    public void deleteMember(@RequestBody String id) {

        log.info("2f8syvg :: member deletion process id: {}", id);

        libraryMemberService.deleteMember(id);

    }

    @PutMapping("/member")
    public LibraryMember updateMember(@RequestBody @Valid MemberDto member) {

        log.info("2f8syvg :: member update process: {}", member);

        return libraryMemberService.save(member);

    }

}
