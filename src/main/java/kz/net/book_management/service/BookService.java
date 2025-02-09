package kz.net.book_management.service;

import kz.net.book_management.model.dto.BookCreateDto;
import kz.net.book_management.model.dto.BookDto;
import kz.net.book_management.model.entity.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {

    List<BookDto> getBooksByAuthor(UUID id);

    void deleteBookById(String id);

    List<BookDto> getAllBooks();

    Book saveBook(BookCreateDto bookDto);

    List<String> getGenres();

}
