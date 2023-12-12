package com.example.jsonProcessingExerciseProductsShop.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "users")
public class User extends BaseEntity{

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name", nullable = false)
    private String lastName;

    @Column (name = "age")
    private int age;

    @ManyToMany
    Set<User> friends;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    Set<Product> products;

    public User() {
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
