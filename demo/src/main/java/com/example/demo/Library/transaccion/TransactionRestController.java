package com.example.demo.Library.transaccion;

import java.util.Objects;
import java.util.Optional;

import com.example.demo.Library.book.BookEntity;
import com.example.demo.Library.book.BookRepository;
import com.example.demo.Library.user.UserEntity;
import com.example.demo.Library.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TransactionRestController {
    @Autowired
    private TransactionRepository transaccionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/transaction")
    public ResponseEntity<Iterable<TransactionEntity>> getAllTransactions() {
        return new ResponseEntity<Iterable<TransactionEntity>>(transaccionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionEntity> getTransaction(@PathVariable Long id) {
        Optional<TransactionEntity> optionalTransaction = transaccionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            return new ResponseEntity<TransactionEntity>(optionalTransaction.get(), HttpStatus.OK);
        }
        return new ResponseEntity<TransactionEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionEntity transaction,
            @RequestParam(name = "user", required = true) Long idUser,
            @RequestParam(name = "book", required = true) Long idBook) {
        try {
            UserEntity user = userRepository.findById(idUser).get();
            BookEntity book = bookRepository.findById(idBook).get();
            transaction.setUser(user);
            transaction.setBook(book);
            if (Objects.isNull(transaction.getDateTime())) {
                transaction.setDateTimeNow();
            }

            return new ResponseEntity<TransactionEntity>(transaccionRepository.save(transaction), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<TransactionEntity>(HttpStatus.BAD_REQUEST);
        }

    }

}
