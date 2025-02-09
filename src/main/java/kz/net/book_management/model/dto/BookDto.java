package kz.net.book_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    public String id;
    public String title;
    public String releaseYear;
    public Integer totalCopies;
    public String genre;
}
