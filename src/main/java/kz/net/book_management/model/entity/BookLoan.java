package kz.net.book_management.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BookLoan extends BaseEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private LibraryMember member;

    @Column
    private Instant loanDate;

    @Column
    private Instant returnDate;

}
