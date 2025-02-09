package kz.net.book_management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.net.book_management.model.dto.AuthorCreateDto;
import kz.net.book_management.model.dto.AuthorDto;
import kz.net.book_management.model.entity.Author;
import kz.net.book_management.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author-rest")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Operation(summary = "Список всех авторов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Список авторов", content = {@Content(mediaType = "application/json")},
                    useReturnTypeSchema = true),
            @ApiResponse(responseCode = "500",
                    description = "Внутренние ошибки", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})})
    @GetMapping("/authors")
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAll();
    }

    @Operation(summary = "Создание автора")
    @PostMapping("/author")
    public Author saveAuthor(@RequestBody @Valid AuthorCreateDto author) {

        log.info("RUQMp7rvzXN :: author creation process : {}", author);

        return authorService.saveAuthor(author);

    }

    @Operation(summary = "Удаление автора по id")
    @DeleteMapping("/author")
    public void deleteAuthor(@RequestParam String id) {

        log.info("yJfJIuOI :: author deleting process id: {}", id);

        authorService.delete(id);

    }

    @Operation(summary = "Обновление автора по id")
    @PutMapping("/author")
    public Author editAuthor(@RequestBody @Valid AuthorCreateDto author) {

        log.info("PS8rto3J :: author editing process : {}", author);

        return authorService.saveAuthor(author);
    }

}
