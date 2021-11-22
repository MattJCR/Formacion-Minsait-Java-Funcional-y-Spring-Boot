package com.example.demo.Library.book;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.Library.library.LibraryEntity;
import com.example.demo.Library.transaccion.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "transaction" })
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_entity_id")
    private LibraryEntity library;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<TransactionEntity> transaction;

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

    public BookEntity(String isbm, String title, String author, String description,
            List<TransactionEntity> transaccion) {
        this.title = title;
        this.author = author;
        this.isbm = isbm;
        this.description = description;
        this.transaction = transaccion;
    }

    @Override
    public String toString() {
        return "ISBM: " + this.isbm + " - Title: " + this.title + " - author: " + this.author;
    }

}
