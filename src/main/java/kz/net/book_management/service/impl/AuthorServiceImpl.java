package kz.net.book_management.service.impl;

import kz.net.book_management.model.dto.AuthorCreateDto;
import kz.net.book_management.model.dto.AuthorDto;
import kz.net.book_management.model.entity.Author;
import kz.net.book_management.repository.AuthorRepository;
import kz.net.book_management.service.AuthorService;
import kz.net.book_management.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    @Override
    public List<AuthorDto> getAll() {

        log.trace("zKklSn3GjEn :: getAll");

        List<Author> authors = authorRepository.findAll();

        List<AuthorDto> authorDtoList = authors.stream()
                .parallel()
                .map(author -> {
                    AuthorDto authorDto = new AuthorDto();
                    authorDto.id = author.getId().toString();
                    authorDto.fullName = author.getFullName();
                    authorDto.birthDate = author.getBirthDate();
                    authorDto.createdAt = author.getCreatedAt();
                    authorDto.email = author.getEmail();

                    authorDto.books = bookService.getBooksByAuthor(author.getId());

                    return authorDto;
                })
                .toList();

        log.debug("t5RtVhVCSr :: authors list : {}", authorDtoList);

        return authorDtoList;

    }

    @Override
    public Author saveAuthor(AuthorCreateDto author) {

        Author authorEntity = new Author();

        if (author.id == null) {

            authorEntity.setEmail(author.email);

            log.info("yhwZEgZ :: will create new author");

        } else {

            authorEntity = authorRepository.findAuthorById(UUID.fromString(author.id))
                    .orElseThrow(() -> new RuntimeException("Author with id : " + author.id + " not found"));

            log.info("q7gUoyYrN :: author with id : {} exists, will update", author.id);

        }

        authorEntity.setFullName(author.fullName);
        authorEntity.setBirthDate(author.birthDate);

        log.info("qsRQnBAf :: going to save author : {}", authorEntity);

        return authorRepository.save(authorEntity);

    }

    @Override
    public void delete(String id) {

        Optional<Author> authorOpt = authorRepository.findAuthorById(UUID.fromString(id));

        Author author = authorOpt.orElseThrow(
                () -> new RuntimeException("WjlNzRrrz :: Author with id " + id + " not found"));

        log.info("jSgto3a6HUK :: author: {} will be deleted", author);

        authorRepository.delete(author);

        log.info("ZehQeHEwU6 :: author with id {} deleted", id);

    }

    @Override
    public Author getAuthor(String authorId) {

        log.info("ATygGYgPrq :: getAuthor id: {}", authorId);

        return authorRepository.findAuthorById(UUID.fromString(authorId))
                .orElse(null);

    }

}
