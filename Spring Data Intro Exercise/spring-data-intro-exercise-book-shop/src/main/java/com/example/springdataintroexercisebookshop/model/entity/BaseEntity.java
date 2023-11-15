package com.example.springdataintroexercisebookshop.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
}
