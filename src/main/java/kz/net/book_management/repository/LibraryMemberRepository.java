package kz.net.book_management.repository;

import kz.net.book_management.entity.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember, UUID> {
}
