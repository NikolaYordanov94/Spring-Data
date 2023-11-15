package com.example.springdataintroexercisebookshop.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table (name = "books")
public class Book extends BaseEntity{

    @Column (name = "title", length = 50, nullable = false)
    private String title;

    @Column (name = "description", length = 1000)
    private String description;

    @Enumerated (EnumType.STRING)
    private EditionType editionType;

    @Column (name = "price", nullable = false)
    private BigDecimal price;

    @Column (name = "copies", nullable = false)
    private Integer copies;

    @Column (name = "release_date")
    private LocalDate releaseDate;

    @Enumerated (EnumType.STRING)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Set<Category> categories;

    public Book(EditionType editionType, LocalDate releaseDate, Integer copies, BigDecimal price, AgeRestriction ageRestriction, String title, Author author, Set<Category> categories) {
        this.editionType = editionType;
        this.releaseDate = releaseDate;
        this.copies = copies;
        this.price = price;
        this.ageRestriction = ageRestriction;
        this.title = title;
        this.author = author;
        this.categories = categories;
    }
}
