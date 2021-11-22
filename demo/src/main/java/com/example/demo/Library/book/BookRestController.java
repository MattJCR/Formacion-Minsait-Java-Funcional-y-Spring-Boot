package com.example.demo.Library.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
public class BookRestController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/book")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> bookList = new ArrayList<BookDto>();
        bookRepository.findAll().forEach(b -> bookList.add(new BookDto(b)));
        return new ResponseEntity<List<BookDto>>(bookList, HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        Optional<BookEntity> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            BookDto bookDto = new BookDto(optionalBook.get());
            return new ResponseEntity<BookDto>(bookDto, HttpStatus.OK);
        }
        return new ResponseEntity<BookDto>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/book")
    public ResponseEntity<BookEntity> createBook(@RequestBody BookEntity book) {
        try {
            return new ResponseEntity<BookEntity>(bookRepository.save(book), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<BookEntity>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/book/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookEntity book,
            @RequestParam(name = "renttouser", required = false) Long idUser) {
        try {
            BookEntity bookToUpdate = bookRepository.findById(id).get();
            Boolean available = false;
            // if (Objects.isNull(bookToUpdate.getUser())) {
            // available = true;
            // } else {
            // available = false;
            // }
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setDescription(book.getDescription());
            bookToUpdate.setIsbm(book.getIsbm());
            bookToUpdate.setTitle(book.getTitle());
            // if (Objects.nonNull(idUser) && available) {
            // bookToUpdate.setUser(userRepository.findById(idUser).get());
            // available = false;
            // }
            BookDto bookDto = new BookDto(bookRepository.save(bookToUpdate));
            bookDto.checkAvailable();
            return new ResponseEntity<BookDto>(bookDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<BookDto>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/book/{id}")
    public ResponseEntity<BookDto> patchBook(@PathVariable Long id, @RequestBody BookEntity book,
            @RequestParam(name = "renttouser", required = false) Long idUser) {
        try {
            BookEntity bookToUpdate = bookRepository.findById(id).get();
            Boolean available = false;
            // if (Objects.isNull(bookToUpdate.getUser())) {
            // available = true;
            // } else {
            // available = false;
            // }
            if (Objects.nonNull(book.getAuthor())) {
                bookToUpdate.setAuthor(book.getAuthor());
            }
            if (Objects.nonNull(book.getDescription())) {
                bookToUpdate.setDescription(book.getDescription());
            }
            if (Objects.nonNull(book.getIsbm())) {
                bookToUpdate.setIsbm(book.getIsbm());
            }
            if (Objects.nonNull(book.getTitle())) {
                bookToUpdate.setTitle(book.getTitle());
            }
            // if (Objects.nonNull(idUser) && available) {
            // bookToUpdate.setUser(userRepository.findById(idUser).get());
            // available = false;
            // }
            BookDto bookDto = new BookDto(bookRepository.save(bookToUpdate));
            bookDto.checkAvailable();
            return new ResponseEntity<BookDto>(bookDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<BookDto>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
        }
    }

}
