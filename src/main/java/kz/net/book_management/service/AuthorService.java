package kz.net.book_management.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kz.net.book_management.model.dto.AuthorCreateDto;
import kz.net.book_management.model.dto.AuthorDto;
import kz.net.book_management.model.entity.Author;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAll();

    Author saveAuthor(AuthorCreateDto author);

    void delete(@NotBlank String id);

    Author getAuthor(@NotBlank String authorId);

}
