package com.example.demo.Library.book;

import java.util.Objects;

import com.example.demo.Library.library.LibraryEntity;
import com.example.demo.Library.user.UserEntity;

public class BookDto {
    private Long id;
    private String isbm;
    private String title;
    private String author;
    private String description;
    private Boolean available;
    private UserEntity user;
    private LibraryEntity library;

    public BookDto() {

    }

    public BookDto(BookEntity book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbm = book.getIsbm();
        this.description = book.getDescription();
        // this.user = book.getUser();
        // this.library = book.getLibrary();
        this.checkAvailable();
    }

    public void checkAvailable() {
        if (Objects.nonNull(this.getUser())) {
            this.available = false;
        } else {
            this.available = true;
        }
    }

    public UserEntity getUser() {
        return user;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public LibraryEntity getLibrary() {
        return library;
    }

    public Long getId() {
        return id;
    }

    public String getIsbm() {
        return isbm;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
