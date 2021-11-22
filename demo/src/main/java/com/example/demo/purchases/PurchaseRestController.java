package com.example.demo.purchases;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import com.example.demo.beer.BeerEntity;
import com.example.demo.beer.BeerRepository;
import com.example.demo.pub.PubEntity;
import com.example.demo.pub.PubRepository;

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
public class PurchaseRestController {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private PubRepository pubRepository;

    @GetMapping("/purchase")
    public ResponseEntity<Iterable<PurchaseEntity>> getAllPurchases() {
        return new ResponseEntity<Iterable<PurchaseEntity>>(purchaseRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/purchase/{id}")
    public ResponseEntity<PurchaseEntity> getPurchase(@PathVariable Long id) {
        Optional<PurchaseEntity> optionalPurchase = purchaseRepository.findById(id);
        if (optionalPurchase.isPresent()) {
            return new ResponseEntity<PurchaseEntity>(optionalPurchase.get(), HttpStatus.OK);
        }
        return new ResponseEntity<PurchaseEntity>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/purchase")
    public ResponseEntity<PurchaseEntity> createPurchase(@RequestBody PurchaseEntity purchase,
    @RequestParam(name = "id_beer", required = true) Long idBeer,
    @RequestParam(name = "id_pub", required = true) Long idPub) {

        try {
            PubEntity pub = pubRepository.findById(idPub).get();
            BeerEntity beer = beerRepository.findById(idBeer).get();
            purchase.setBeer(beer);
            purchase.setMyPub(pub);
            purchase.setDate(LocalDateTime.now());
            return new ResponseEntity<PurchaseEntity>(purchaseRepository.save(purchase), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<PurchaseEntity>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/purchase/{id}")
    public ResponseEntity<PurchaseEntity> updatePurchase(@PathVariable Long id, @RequestBody PurchaseEntity purchase) {
        try {
            PurchaseEntity purchaseToUpdate = purchaseRepository.findById(id).get();
            purchaseToUpdate.setPrice(purchase.getPrice());
            purchaseToUpdate.setQuantity(purchase.getQuantity());
            purchaseToUpdate.setDate(purchase.getDate());
            return new ResponseEntity<PurchaseEntity>(purchaseRepository.save(purchaseToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<PurchaseEntity>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/purchase/{id}")
    public ResponseEntity<PurchaseEntity> softUpdatePurchase(@PathVariable Long id, @RequestBody PurchaseEntity purchase) {
        try {
            PurchaseEntity purchaseToUpdate = purchaseRepository.findById(id).get();
            if(Objects.nonNull(purchase.getPrice())){
                purchaseToUpdate.setPrice(purchase.getPrice());
            }
            if(Objects.nonNull(purchase.getQuantity())){
                purchaseToUpdate.setQuantity(purchase.getQuantity());
            }
            if(Objects.nonNull(purchase.getDate())){
                purchaseToUpdate.setDate(purchase.getDate());
            }
            return new ResponseEntity<PurchaseEntity>(purchaseRepository.save(purchaseToUpdate), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<PurchaseEntity>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/purchase/{id}")
    public ResponseEntity<PurchaseEntity> deletePurchase(@PathVariable Long id) {
        PurchaseEntity purchaseToDelete = new PurchaseEntity();
        purchaseToDelete.setId(id);
        try {
            purchaseRepository.deleteById(id);
            return new ResponseEntity<PurchaseEntity>(purchaseToDelete, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<PurchaseEntity>(purchaseToDelete, HttpStatus.NOT_FOUND);
        }
    }
}
