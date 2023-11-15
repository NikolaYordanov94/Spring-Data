package com.example.springdataintroexercisebookshop;

import com.example.springdataintroexercisebookshop.service.AuthorService;
import com.example.springdataintroexercisebookshop.service.BookService;
import com.example.springdataintroexercisebookshop.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);
    
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedAllData(categoryService, authorService, bookService);

        System.out.println("Do you want to continue with the tasks, choose a task from 1 to 4 or No: ");
        String continueWithTasks = scanner.nextLine();

        while (!continueWithTasks.equals("No")){
            int numberOfTask = Integer.parseInt(continueWithTasks);

            switch (numberOfTask) {
                case 1 -> printAllBooksAfter2000(2000);
                case 2 -> printAllAuthorsNamesWithBooksWithReleaseDateBefore1990(1990);
                case 3 -> printAllAuthorsOrderedByTheirBooks();
                case 4 -> printAllBooksByAuthorNameOrderedByReleaseDate("George", "Powell");
            }

            continueWithTasks = scanner.nextLine();
        }

    }

    private void printAllBooksByAuthorNameOrderedByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstNameAndLastNameOrderedByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsOrderedByTheirBooks() {
        authorService
                .getAllAuthorsOrderedByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBefore1990(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    public void seedAllData(CategoryService categoryService, AuthorService authorService, BookService bookService) throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }

    private void printAllBooksAfter2000(int year){
        bookService.findAllBooksAfterYear(year)
                .forEach(book -> System.out.println(book.getTitle()));
    }
}
