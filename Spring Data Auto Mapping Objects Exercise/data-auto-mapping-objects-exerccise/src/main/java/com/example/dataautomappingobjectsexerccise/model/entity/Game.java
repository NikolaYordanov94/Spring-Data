package com.example.dataautomappingobjectsexerccise.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table (name = "games")
public class Game extends BaseEntity{

    @Column (name = "title")
    private String title;

    @Column (name = "trailer")
    private String trailer;

    @Column (name = "image_thumbnail")
    private String imageThumbnail;

    @Column (name = "size")
    private Double size;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column (name = "price")
    private BigDecimal price;

    @Column (name = "description", columnDefinition = "TEXT")
    private String description;

    @Column (name = "release_date")
    private LocalDate releaseDate;

    public Game() {
    }
}
