package kz.net.book_management.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column
    private String releaseYear;

    @Column
    private Integer totalCopies;

    @Column
    private String genre;

    @ManyToOne
    private Author author;

}
