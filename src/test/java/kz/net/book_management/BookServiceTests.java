package kz.net.book_management;

import kz.net.book_management.model.dto.BookCreateDto;
import kz.net.book_management.model.entity.Author;
import kz.net.book_management.model.entity.Book;
import kz.net.book_management.model.enums.GenreEnum;
import kz.net.book_management.repository.BookRepository;
import kz.net.book_management.service.AuthorService;
import kz.net.book_management.service.BookService;
import kz.net.book_management.service.impl.AuthorServiceImpl;
import kz.net.book_management.service.impl.BookServiceImpl;
import kz.net.book_management.utils.RND;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTests {

    @InjectMocks
    private BookService bookService = new BookServiceImpl();

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService = new AuthorServiceImpl();

    @Test
    void getBook_whenBookExists() {

        UUID id = UUID.randomUUID();

        Book book = new Book();
        book.setId(id);
        book.setTitle(RND.strEng(10));

        int randomYear = (int) ((Math.random() * (2025 - 1900 + 1)) + 1900);

        book.setReleaseYear(String.valueOf(randomYear));
        book.setGenre(GenreEnum.HORROR.get());

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        //
        //
        Book expectedBook = bookService.getBook(id.toString());
        //
        //

        assertThat(expectedBook).isNotNull();
        assertThat(expectedBook.getId()).isEqualTo(book.getId());
        assertThat(expectedBook.getGenre()).isEqualTo(book.getGenre());
        assertThat(expectedBook.getTitle()).isEqualTo(book.getTitle());

    }

    @Test
    void deleteBook_whenBookDoesNotExist() {

        UUID id = UUID.randomUUID();

        when(bookRepository.findById(id)).thenReturn(null);

        //
        //
        assertThrows(RuntimeException.class, () -> {
            bookService.deleteBookById(id.toString());
        });
        //
        //

    }

    @Test
    void saveBook_ShouldCreateNewBook_WhenDtoIsValid() {
        BookCreateDto bookDto = new BookCreateDto();
        bookDto.id = null; // Creating a new book
        bookDto.title = "Абай жолы";
        bookDto.genre = "Novel";
        bookDto.totalCopies = 10;
        bookDto.releaseYear = "1942";
        bookDto.authorId = UUID.randomUUID().toString();

        Author author = new Author();
        author.setId(UUID.fromString(bookDto.authorId));

        Book savedBook = new Book();
        savedBook.setId(UUID.randomUUID());
        savedBook.setTitle(bookDto.title);
        savedBook.setGenre(bookDto.genre);
        savedBook.setTotalCopies(bookDto.totalCopies);
        savedBook.setReleaseYear(bookDto.releaseYear);
        savedBook.setAuthor(author);

        when(authorService.getAuthor(bookDto.authorId)).thenReturn(author);
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        //
        //
        Book result = bookService.saveBook(bookDto);
        //
        //

        assertNotNull(result);
        assertEquals(bookDto.title, result.getTitle());
        assertEquals(bookDto.genre, result.getGenre());
        assertEquals(bookDto.totalCopies, result.getTotalCopies());
        assertEquals(bookDto.releaseYear, result.getReleaseYear());
        assertEquals(author.getId(), result.getAuthor().getId());

        verify(bookRepository).save(any(Book.class));
        verify(authorService).getAuthor(bookDto.authorId);

    }

    @Test
    void saveBook_ShouldThrowException_WhenBookIdNotFound() {
        BookCreateDto bookDto = new BookCreateDto();
        bookDto.id = UUID.randomUUID().toString();

        when(bookRepository.findById(UUID.fromString(bookDto.id))).thenReturn(Optional.empty());

        //
        //
        assertThrows(RuntimeException.class, () -> {
            bookService.saveBook(bookDto);
        });
        //
        //

    }

}

