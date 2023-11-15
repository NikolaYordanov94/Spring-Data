package com.example.springdataintroexercisebookshop.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table (name = "authors")
public class Author extends BaseEntity{

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name", nullable = false)
    private String lastName;

    @OneToMany (mappedBy = "author", fetch = FetchType.EAGER)
    Set<Book> books;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }
}
