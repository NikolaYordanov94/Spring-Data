package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findBookTitlesByAgeRestriction(AgeRestriction ageRestriction);

    List<String> finBookTitlesGoldenEditionWithLessThan5000Copies();

    List<String> findAllBookTitlesAndPricesInPriceInterval();

    List<String> findAllBookTitlesNotReleasedInYear(int year);

    List<String> findAllBookTitlesEditionTypeAndPriceReleasedBeforeDate(String date);

    List<String> findAllBookTitlesContainingSubstring(String substring);

    List<String> findAllBookTitlesWrittenByAuthor(String authorSubstring);

    int findAllBooksCountWithTitleLongerThanGivenNumber(int number);

    String findBookInformationByGivenTitle(String bookTitle);
}
