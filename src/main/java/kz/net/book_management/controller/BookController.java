package kz.net.book_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.net.book_management.model.dto.BookCreateDto;
import kz.net.book_management.model.dto.BookDto;
import kz.net.book_management.model.entity.Book;
import kz.net.book_management.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-rest")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Список всех жанров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Список жанров", content = {@Content(mediaType = "application/json")},
                    useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500",
                    description = "Внутренние ошибки", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})})
    @GetMapping("/genres")
    public List<String> getGenres() {
        return bookService.getGenres();
    }

    @Operation(summary = "Список всех книг")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Список книг", content = {@Content(mediaType = "application/json")},
                    useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500",
                    description = "Внутренние ошибки", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})})
    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Создание книги")
    @PostMapping("/book")
    public Book createBook(@RequestBody @Valid BookCreateDto bookDto) {

        log.info("jDAV6cs :: Create book: {}", bookDto);

        return bookService.saveBook(bookDto);

    }

    @Operation(summary = "Удаление книги")
    @DeleteMapping("/book")
    public void deleteBook(@RequestParam String id) {

        log.info("ZrfCrCW5 :: book deleting process id: {}", id);

        bookService.deleteBookById(id);

    }

    @Operation(summary = "Обновление книги по id")
    @PutMapping("/book")
    public Book editBook(@RequestBody @Valid BookCreateDto book) {

        log.info("OrTqJZ1 :: book editing process : {}", book);

        return bookService.saveBook(book);
    }


}
