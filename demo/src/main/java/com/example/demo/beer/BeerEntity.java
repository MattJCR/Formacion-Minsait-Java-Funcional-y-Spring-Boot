package com.example.demo.beer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BeerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tagline;
    @JsonProperty("first_brewed")
    private String firstBrewed;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @JsonProperty("image_url")
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
    public void setAbv(Double abv) {
        this.abv = abv;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
}
