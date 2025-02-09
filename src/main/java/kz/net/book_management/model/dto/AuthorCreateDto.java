package kz.net.book_management.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorCreateDto {

    public String id;

    @NotBlank
    public String fullName;

    @NotBlank
    public String email;

    @NotBlank
    public Date birthDate;

}
