package com.example.springdataintroexercisebookshop.service;

import com.example.springdataintroexercisebookshop.model.entity.*;
import com.example.springdataintroexercisebookshop.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOK_FILE_PATH =
            "C:\\Users\\Nikola\\Desktop\\Spring Data Projects\\Spring Data Intro Exercise\\spring-data-intro-exercise-book-shop\\src\\main\\resources\\books.txt";

    private BookRepository bookRepository;
    private AuthorService authorService;
    private CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {

        if (bookRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(BOOK_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBook(bookInfo);

                    bookRepository.save(book);
                });

    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {

        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {

        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s",
                        book.getAuthor().getFirstName(), book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstNameAndLastNameOrderedByReleaseDate(String firstName, String lastName) {

        return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(), book.getReleaseDate(), book.getCopies()))
                .collect(Collectors.toList());
    }


    public Book createBook(String[] bookDetails){
        EditionType editionType = EditionType.values()[Integer.parseInt(bookDetails[0])];
        LocalDate releaseDate = LocalDate.parse(bookDetails[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookDetails[2]);
        BigDecimal price = new BigDecimal(bookDetails[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookDetails[4])];
        String title = Arrays.stream(bookDetails)
                .skip(5)
                .collect(Collectors.joining(" "));
        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }
}
