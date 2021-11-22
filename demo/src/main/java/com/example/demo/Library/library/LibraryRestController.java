package com.example.demo.Library.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.Library.book.BookDto;
import com.example.demo.Library.book.BookEntity;
import com.example.demo.Library.book.BookRepository;
import com.example.demo.Library.user.UserEntity;
import com.example.demo.Library.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class LibraryRestController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    LibraryRepository libraryRepository;

    @GetMapping("/library")
    public ResponseEntity<Iterable<LibraryEntity>> getAllLibraries() {
        Iterable<LibraryEntity> libraryList = libraryRepository.findAll();
        libraryList.forEach(l -> l.getBooks());
        return new ResponseEntity<Iterable<LibraryEntity>>(libraryList, HttpStatus.OK);
    }

    @GetMapping("/library/{id}")
    public ResponseEntity<LibraryEntity> getLibrary(@PathVariable Long id) {
        Optional<LibraryEntity> optionalLibrary = libraryRepository.findById(id);
        if (optionalLibrary.isPresent()) {
            return new ResponseEntity<LibraryEntity>(optionalLibrary.get(), HttpStatus.OK);
        }
        return new ResponseEntity<LibraryEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/library")
    public ResponseEntity<LibraryEntity> createLibrary(@RequestBody LibraryEntity library) {
        try {
            return new ResponseEntity<LibraryEntity>(libraryRepository.save(library), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<LibraryEntity>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/library/{id}")
    public ResponseEntity<LibraryEntity> updateBook(@PathVariable Long id, @RequestBody LibraryEntity library,
            @RequestParam(name = "adduser", required = false) Long idUser,
            @RequestParam(name = "addbook", required = false) Long idBook) {
        try {
            LibraryEntity libraryToUpdate = libraryRepository.findById(id).get();
            libraryToUpdate.setNombre(library.getNombre());
            libraryToUpdate.setUbicacion(library.getUbicacion());
            if (Objects.nonNull(idUser)) {
                List<UserEntity> libraryUsers = new ArrayList<UserEntity>();
                libraryUsers.add(userRepository.findById(idUser).get());
                libraryToUpdate.setLibraryUsers(libraryUsers);
            }
            if (Objects.nonNull(idBook)) {
                List<BookEntity> libraryBooks = new ArrayList<BookEntity>();
                libraryBooks.add(bookRepository.findById(idBook).get());
                libraryToUpdate.setBooks(libraryBooks);
            }
            return new ResponseEntity<LibraryEntity>(libraryRepository.save(libraryToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<LibraryEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    // @PatchMapping("/book/{id}")
    // public ResponseEntity<BookDto> patchBook(@PathVariable Long id, @RequestBody
    // BookEntity book,
    // @RequestParam(name = "renttouser", required = false) Long idUser) {
    // try {
    // BookEntity bookToUpdate = bookRepository.findById(id).get();
    // Boolean available = false;
    // if (Objects.isNull(bookToUpdate.getUser())) {
    // available = true;
    // } else {
    // available = false;
    // }
    // if (Objects.nonNull(book.getAuthor())) {
    // bookToUpdate.setAuthor(book.getAuthor());
    // }
    // if (Objects.nonNull(book.getDescription())) {
    // bookToUpdate.setDescription(book.getDescription());
    // }
    // if (Objects.nonNull(book.getIsbm())) {
    // bookToUpdate.setIsbm(book.getIsbm());
    // }
    // if (Objects.nonNull(book.getTitle())) {
    // bookToUpdate.setTitle(book.getTitle());
    // }
    // if (Objects.nonNull(idUser) && available) {
    // bookToUpdate.setUser(userRepository.findById(idUser).get());
    // available = false;
    // }
    // BookDto bookDto = new BookDto(bookRepository.save(bookToUpdate));
    // bookDto.checkAvailable();
    // return new ResponseEntity<BookDto>(bookDto, HttpStatus.ACCEPTED);
    // } catch (Exception e) {
    // return new ResponseEntity<BookDto>(HttpStatus.BAD_REQUEST);
    // }

    // }

    // @DeleteMapping("/book/{id}")
    // public ResponseEntity<Long> deleteBook(@PathVariable Long id) {
    // try {
    // bookRepository.deleteById(id);
    // return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
    // } catch (Exception e) {
    // return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
    // }
    // }

}
