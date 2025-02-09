package kz.net.book_management.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString
public class BookCreateDto {

    public String id;

    @NotBlank
    public String title;

    @NotBlank
    public String releaseYear;

    @NotBlank
    public Integer totalCopies;

    @NotBlank
    public String genre;

    @NotBlank
    public String authorId;

}
