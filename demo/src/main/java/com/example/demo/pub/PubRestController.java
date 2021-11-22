package com.example.demo.pub;

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
public class PubRestController {
    @Autowired
    private PubRepository pubRepository;
    @GetMapping("/pub")
    public ResponseEntity<Iterable<PubEntity>> getAllPubs() {
        return new ResponseEntity<Iterable<PubEntity>>(pubRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/pub/{id}")
    public ResponseEntity<PubEntity> getPub(@PathVariable Long id) {
        Optional<PubEntity> optionalPub = pubRepository.findById(id);
        if (optionalPub.isPresent()) {
            return new ResponseEntity<PubEntity>(optionalPub.get(), HttpStatus.OK);
        }
        return new ResponseEntity<PubEntity>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/pub")
    public ResponseEntity<PubEntity> createPub(@RequestBody PubEntity pub) {
        try {
            return new ResponseEntity<PubEntity>(pubRepository.save(pub), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<PubEntity>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/pub/{id}")
    public ResponseEntity<PubEntity> updatePub(@PathVariable Long id, @RequestBody PubEntity pub) {
        try {
            PubEntity pubToUpdate = pubRepository.findById(id).get();
            pubToUpdate.setName(pub.getName());
            pubToUpdate.setLocation(pub.getLocation());
            pubToUpdate.setPhone(pub.getPhone());
            return new ResponseEntity<PubEntity>(pubRepository.save(pubToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<PubEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/pub/{id}")
    public ResponseEntity<PubEntity> softUpdatePub(@PathVariable Long id, @RequestBody PubEntity pub) {
        try {
            PubEntity pubToUpdate = pubRepository.findById(id).get();
            if(Objects.nonNull(pub.getName())){
                pubToUpdate.setName(pub.getName());
            }
            if(Objects.nonNull(pub.getLocation())){
                pubToUpdate.setLocation(pub.getLocation());
            }
            if(Objects.nonNull(pub.getPhone())){
                pubToUpdate.setPhone(pub.getPhone());
            }
            return new ResponseEntity<PubEntity>(pubRepository.save(pubToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<PubEntity>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/pub/{id}")
    public ResponseEntity<PubEntity> deletePub(@PathVariable Long id) {
        PubEntity pubToDelete = new PubEntity();
        pubToDelete.setId(id);
        try {
            pubRepository.deleteById(id);
            return new ResponseEntity<PubEntity>(pubToDelete, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<PubEntity>(pubToDelete, HttpStatus.NOT_FOUND);
        }
    }
}
