package kz.net.book_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorDto {
    public String id;
    public String fullName;
    public String email;
    public Date birthDate;
    public Instant createdAt;
    public List<BookDto> books;
}
