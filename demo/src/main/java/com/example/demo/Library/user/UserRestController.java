package com.example.demo.Library.user;

import java.util.Objects;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
        return new ResponseEntity<Iterable<UserEntity>>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return new ResponseEntity<UserEntity>(optionalUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        try {
            return new ResponseEntity<UserEntity>(userRepository.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        try {
            UserEntity userToUpdate = userRepository.findById(id).get();
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setEmail(user.getEmail());
            return new ResponseEntity<UserEntity>(userRepository.save(userToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<UserEntity> patchBook(@PathVariable Long id, @RequestBody UserEntity user) {
        try {
            UserEntity userToUpdate = userRepository.findById(id).get();
            if (Objects.nonNull(user.getFirstName())) {
                userToUpdate.setFirstName(user.getFirstName());
            }
            if (Objects.nonNull(user.getLastName())) {
                userToUpdate.setLastName(user.getLastName());
            }
            if (Objects.nonNull(user.getEmail())) {
                userToUpdate.setEmail(user.getEmail());
            }
            return new ResponseEntity<UserEntity>(userRepository.save(userToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<Long>(id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<Long>(id, HttpStatus.NOT_FOUND);
        }
    }
}
