package kz.net.book_management.service.impl;

import kz.net.book_management.model.dto.BookCreateDto;
import kz.net.book_management.model.dto.BookDto;
import kz.net.book_management.model.entity.Author;
import kz.net.book_management.model.entity.Book;
import kz.net.book_management.model.enums.GenreEnum;
import kz.net.book_management.repository.BookRepository;
import kz.net.book_management.service.AuthorService;
import kz.net.book_management.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    public List<BookDto> getBooksByAuthor(UUID id) {

        log.debug("UjD70BQ0c :: calling getBooksByAuthor by id: {}", id);

        List<Book> books = bookRepository.findAllByAuthorId(id);

        List<BookDto> bookDtos = new ArrayList<>();

        if (books != null) {
            books.forEach(book -> {
                BookDto bookDto = new BookDto();
                bookDto.id = book.getId().toString();
                bookDto.title = book.getTitle();
                bookDto.genre = book.getGenre();
                bookDto.releaseYear = book.getReleaseYear();
                bookDto.totalCopies = book.getTotalCopies();
                bookDtos.add(bookDto);
            });
        }

        return bookDtos;

    }

    @Override
    public void deleteBookById(String id) {

        Optional<Book> bookOpt = bookRepository.findById(UUID.fromString(id));

        Book book = bookOpt.orElseThrow(
                () -> new RuntimeException("OVe9yRiC :: book with id " + id + " not found"));

        log.info("G9wPDXRu :: book: {} will be deleted", book);

        bookRepository.delete(book);

        log.info("H75Otvu4KD :: book with id {} deleted", id);

    }

    @Override
    public List<BookDto> getAllBooks() {

        log.debug("UjD70BQ0c :: calling getAllBooks");

        return bookRepository
                .findAll()
                .stream()
                .map((book -> {
                    BookDto bookDto = new BookDto();
                    bookDto.id = book.getId().toString();
                    bookDto.title = book.getTitle();
                    bookDto.totalCopies = book.getTotalCopies();
                    bookDto.releaseYear = book.getReleaseYear();
                    bookDto.author = book.getAuthor().getFullName();

                    return bookDto;
                }))
                .toList();
    }

    @Override
    public Book saveBook(BookCreateDto bookDto) {

        Book book = new Book();

        if (bookDto.id != null) {

            book = bookRepository.findById(UUID.fromString(bookDto.id))
                    .orElseThrow(() -> new RuntimeException("Book with id " + bookDto.id + " not found"));

            log.info("DT7LckEgfY :: will create new book");

        }

        book.setTitle(bookDto.title);
        book.setGenre(bookDto.genre);
        book.setTotalCopies(bookDto.totalCopies);
        book.setReleaseYear(bookDto.releaseYear);

        Author author = authorService.getAuthor(bookDto.authorId);

        if (author == null) {
            throw new RuntimeException("p1Dcls2u :: author with id " + author.getId() + " not exists");
        }

        book.setAuthor(author);

        log.info("Oc6qQWYhUo :: going to save book : {}", book);

        return bookRepository.save(book);

    }

    @Override
    public List<String> getGenres() {

        log.trace("dqB2uqn3Xmb :: calling getGenres");

        return Arrays.stream(GenreEnum.values())
                .map(GenreEnum::get)
                .collect(Collectors.toList());
    }

    @Override
    public Book getBook(String id) {

        log.info("GlcLjgDCW :: getBook id: {}", id);

        return bookRepository.findById(UUID.fromString(id))
                .orElse(null);

    }
}
