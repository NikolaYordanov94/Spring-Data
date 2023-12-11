package com.example.dataautomappingobjectsexerccise.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "users")
public class User extends BaseEntity{

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "full_name")
    private String fullName;

    @Column (name = "is_administrator")
    private boolean isAdministrator;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @ManyToMany
    private List<Game> games;

    public User() {
    }
}
