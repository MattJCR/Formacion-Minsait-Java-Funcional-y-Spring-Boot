package com.example.demo.beer;

public class BeerResponse {
    String name;
    String tagline;
    String first_brewed;
    String description;
    String image_url;
    Double abv;

    public BeerResponse() {

    }

    public BeerResponse(String name, String tagline, String first_brewed, String description, String image_url,
            Double abv) {
        this.name = name;
        this.tagline = tagline;
        this.first_brewed = first_brewed;
        this.description = description;
        this.image_url = image_url;
        this.abv = abv;
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

    public String getimage_url() {
        return image_url;
    }

    public Double getAbv() {
        return abv;
    }
}
