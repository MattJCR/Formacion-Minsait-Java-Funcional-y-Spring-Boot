package com.example.demo.purchases;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.beer.BeerEntity;
import com.example.demo.pub.PubEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private Integer quantity;
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beer_entity_id")
    private BeerEntity beer;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pub_entity_id")
    private PubEntity myPub;
    
    public BeerEntity getBeer() {
        return beer;
    }
    public PubEntity getMyPub() {
        return myPub;
    }
    public void setBeer(BeerEntity beer) {
        this.beer = beer;
    }
    public void setMyPub(PubEntity myPub) {
        this.myPub = myPub;
    }
    public Long getId() {
        return id;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
