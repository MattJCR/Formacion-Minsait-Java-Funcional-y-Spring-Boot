package com.example.demo.beer;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/")
public class BeerRestController {
    @Autowired
    private BeerRepository beerRepository;
    @Autowired
    private BeerService beerService;

    @GetMapping("/beer")
    public ResponseEntity<List<BeerEntity>> getAllBeers(@RequestParam(name = "abv_gt", required = false) Double abvGt,
            @RequestParam(name = "abv_lt", required = false) Double abvLt) {

        return new ResponseEntity<List<BeerEntity>>(beerService.getAllBeers(abvLt,abvGt), HttpStatus.OK);

    }
    @PostMapping("/beer")
    public ResponseEntity<BeerEntity> createBeer(@RequestBody BeerEntity beer) {
        try {
            System.out.println(beer.getName());
            return new ResponseEntity<BeerEntity>(beerRepository.save(beer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<BeerEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/beer/{id}")
    public ResponseEntity<BeerEntity> getBook(@PathVariable Long id) {
        Optional<BeerEntity> optionalBeer = beerRepository.findById(id);
        if (optionalBeer.isPresent()) {
            return new ResponseEntity<BeerEntity>(optionalBeer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<BeerEntity>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/beer/{id}")
    public ResponseEntity<BeerEntity> updateBeer(@PathVariable Long id, @RequestBody BeerEntity beer) {
        try {
            BeerEntity beerToUpdate = beerRepository.findById(id).get();
            beerToUpdate.setAbv(beer.getAbv());
            beerToUpdate.setDescription(beer.getDescription());
            beerToUpdate.setFirstBrewed(beer.getFirstBrewed());
            beerToUpdate.setImageUrl(beer.getImageUrl());
            beerToUpdate.setName(beer.getName());
            beerToUpdate.setTagline(beer.getTagline());

            return new ResponseEntity<BeerEntity>(beerRepository.save(beerToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<BeerEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/beer/{id}")
    public ResponseEntity<BeerEntity> softUpdateBeer(@PathVariable Long id, @RequestBody BeerEntity beer) {
        try {
            BeerEntity beerToUpdate = beerRepository.findById(id).get();
            if(Objects.nonNull(beer.getAbv())){
                beerToUpdate.setAbv(beer.getAbv());
            }         
            if(Objects.nonNull(beer.getDescription())){
                beerToUpdate.setDescription(beer.getDescription());
            }           
            if(Objects.nonNull(beer.getFirstBrewed())){
                beerToUpdate.setFirstBrewed(beer.getFirstBrewed());
            }            
            if(Objects.nonNull(beer.getImageUrl())){
                beerToUpdate.setImageUrl(beer.getImageUrl());
            }            
            if(Objects.nonNull(beer.getName())){
                beerToUpdate.setName(beer.getName());
            }
            if(Objects.nonNull(beer.getTagline())){
                beerToUpdate.setTagline(beer.getTagline());
            }
            return new ResponseEntity<BeerEntity>(beerRepository.save(beerToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<BeerEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/beer/{id}")
    public ResponseEntity<BeerEntity> deleteBook(@PathVariable Long id) {
        BeerEntity beerToDelete = new BeerEntity();
        beerToDelete.setId(id);
        try {
            beerRepository.deleteById(id);
            
            return new ResponseEntity<BeerEntity>(beerToDelete, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<BeerEntity>(beerToDelete, HttpStatus.NOT_FOUND);
        }
    }
}
