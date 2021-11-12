package com.example.demo.beer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BeerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String tagline;
    private String firstBrewed;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private String imageUrl;
    private Double abv;

    public BeerEntity() {

    }

    public BeerEntity(String name, String tagline, String firstBrewed, String description, String imageUrl,
            Double abv) {
        this.name = name;
        this.tagline = tagline;
        this.firstBrewed = firstBrewed;
        this.description = description;
        this.imageUrl = imageUrl;
        this.abv = abv;
    }

    public Long getId() {
        return id;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getAbv() {
        return abv;
    }
}
