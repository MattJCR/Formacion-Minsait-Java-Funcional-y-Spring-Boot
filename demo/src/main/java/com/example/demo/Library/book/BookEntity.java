package com.example.demo.Library.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.Library.library.LibraryEntity;
import com.example.demo.Library.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BookEntity {
    @Id
    @Column(name = "book_entity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbm;
    private String title;
    private String author;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_entity_id")
    private UserEntity user;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_entity_id")
    private LibraryEntity library;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LibraryEntity getLibrary() {
        return library;
    }

    public void setLibrary(LibraryEntity library) {
        this.library = library;
    }

    public String getAuthor() {
        return author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbm() {
        return isbm;
    }

    public void setIsbm(String isbm) {
        this.isbm = isbm;
    }

    public BookEntity() {

    }

    public BookEntity(String isbm, String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.isbm = isbm;
        this.description = description;
    }

    public BookEntity(String isbm, String title, String author, String description, UserEntity user) {
        this.title = title;
        this.author = author;
        this.isbm = isbm;
        this.description = description;
        this.user = user;
    }

    @Override
    public String toString() {
        return "ISBM: " + this.isbm + " - Title: " + this.title + " - author: " + this.author;
    }

}
