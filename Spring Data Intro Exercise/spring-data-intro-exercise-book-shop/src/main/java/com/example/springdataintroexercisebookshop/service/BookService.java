package com.example.springdataintroexercisebookshop.service;

import com.example.springdataintroexercisebookshop.model.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstNameAndLastNameOrderedByReleaseDate(String firstName, String lastName);
}
