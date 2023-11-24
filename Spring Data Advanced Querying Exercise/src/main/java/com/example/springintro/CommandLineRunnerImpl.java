package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
     //   printAllAuthorsAndNumberOfTheirBooks();
//        printAllBooksByAuthorNameOrderedByReleaseDate("George", "Powell");

        System.out.println("Select exercise number: ");
        int exerciseNum = Integer.parseInt(bufferedReader.readLine());

        switch (exerciseNum) {
            case 1 -> bookTitlesByAgeRestriction();
            case 2 -> bookTitlesGoldenEditionWhichHaveLessThan5000Copies();
            case 3 -> bookTitlesAndPricesWithPriceInPriceInterval();
            case 4 -> bookTitlesNotReleasedInYear();
            case 5 -> bookTitlesEditionTypeAndPriceReleasedBeforeDate();
            case 6 -> authorFirstNameEndingWithSubstring();
            case 7 -> bookTitlesContainingSubstring();
            case 8 -> bookTitlesWrittenByAuthorNameStartingWith();
            case 9 -> bookCountWithTitleLongerThanGivenNumber();
            case 10 -> bookCountCopiesByAuthorOrderDesc();
            case 11 -> bookInformationByGivenTitle();
        }

    }

    private void bookInformationByGivenTitle() throws IOException {
        System.out.println("Enter a book title: ");
        String bookTitle = bufferedReader.readLine();

        System.out.println(bookService
                .findBookInformationByGivenTitle(bookTitle));
    }

    private void bookCountCopiesByAuthorOrderDesc() {
        List<String> result = authorService
                .findAllBookCountCopiesByAuthorOrderDesc();

        Collections.reverse(result);
        result.forEach(System.out::println);
    }

    private void bookCountWithTitleLongerThanGivenNumber() throws IOException {
        System.out.println("Enter a number: ");
        int number = Integer.parseInt(bufferedReader.readLine());

        System.out.println(bookService
                .findAllBooksCountWithTitleLongerThanGivenNumber(number));
    }

    private void bookTitlesWrittenByAuthorNameStartingWith() throws IOException {
        System.out.println("Enter a substring: ");
        String substring = bufferedReader.readLine();

        bookService
                .findAllBookTitlesWrittenByAuthor(substring)
                .forEach(System.out::println);
    }

    private void bookTitlesContainingSubstring() throws IOException {
        System.out.println("Enter a substring: ");
        String substring = bufferedReader.readLine();

        bookService
                .findAllBookTitlesContainingSubstring(substring)
                .forEach(System.out::println);
    }

    private void authorFirstNameEndingWithSubstring() throws IOException {
        System.out.println("Enter a substring: ");
        String substring = bufferedReader.readLine();

        authorService
                .findAllAuthorsWithNameEndingWith(substring)
                .forEach(System.out::println);
    }

    private void bookTitlesEditionTypeAndPriceReleasedBeforeDate() throws IOException {
        System.out.println("Enter date in format dd-mm-yyyy: ");
        String date = bufferedReader.readLine();

        bookService
                .findAllBookTitlesEditionTypeAndPriceReleasedBeforeDate(date)
                .forEach(System.out::println);
    }

    private void bookTitlesNotReleasedInYear() throws IOException {
        System.out.println("Enter year: ");
        int year = Integer.parseInt(bufferedReader.readLine());

        bookService
                .findAllBookTitlesNotReleasedInYear(year)
                .forEach(System.out::println);
    }

    private void bookTitlesAndPricesWithPriceInPriceInterval() {
        bookService
                .findAllBookTitlesAndPricesInPriceInterval()
                .forEach(System.out::println);
    }

    private void bookTitlesGoldenEditionWhichHaveLessThan5000Copies() {
        bookService
                .finBookTitlesGoldenEditionWithLessThan5000Copies()
                .forEach(System.out::println);
    }

    private void bookTitlesByAgeRestriction() throws IOException {
        System.out.println("Enter age restriction: ");
        AgeRestriction ageRestriction = AgeRestriction
                .valueOf(bufferedReader.readLine().toUpperCase());

        bookService
                .findBookTitlesByAgeRestriction(ageRestriction)
                .forEach(System.out::println);
    }

    private void printAllBooksByAuthorNameOrderedByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
