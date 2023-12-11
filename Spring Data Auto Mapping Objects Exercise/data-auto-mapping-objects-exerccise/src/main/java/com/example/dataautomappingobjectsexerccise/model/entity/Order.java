package com.example.dataautomappingobjectsexerccise.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "orders")
public class Order extends BaseEntity{

    public Order() {
    }

    @ManyToOne
    private User buyer;

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @ManyToMany
    private List<Game> games;
}
