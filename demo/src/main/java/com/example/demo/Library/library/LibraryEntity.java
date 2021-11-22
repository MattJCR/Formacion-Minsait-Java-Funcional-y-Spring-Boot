package com.example.demo.Library.library;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.example.demo.Library.book.BookEntity;
import com.example.demo.Library.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class LibraryEntity {
    @Id
    @Column(name = "library_entity_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ubicacion;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private List<BookEntity> books;

    @ManyToMany(mappedBy = "libraries")
    private List<UserEntity> libraryUsers;

    public LibraryEntity() {

    }

    public LibraryEntity(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public LibraryEntity(String nombre, String ubicacion, List<BookEntity> books) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.books = books;
    }

    public List<UserEntity> getLibraryUsers() {
        return libraryUsers;
    }

    public void setLibraryUsers(List<UserEntity> libraryUsers) {
        this.libraryUsers = libraryUsers;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + " - Ubicacion: " + this.ubicacion;
    }
}
