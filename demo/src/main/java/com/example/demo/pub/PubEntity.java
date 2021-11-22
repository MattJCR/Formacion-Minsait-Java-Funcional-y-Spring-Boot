package com.example.demo.pub;

import java.util.List;

import com.example.demo.purchases.PurchaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String phone;
    @JsonManagedReference
    @OneToMany(
        mappedBy = "myPub", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true,
        fetch = FetchType.EAGER)
    private List<PurchaseEntity> purchases;

    public PubEntity(){

    }
    public PubEntity(String name,String location,String phone){
        this.name = name;
        this.location = location;
        this.phone = phone;
    }
    public List<PurchaseEntity> getPurchases() {
        return purchases;
    }
    public void setPurchases(List<PurchaseEntity> purchases) {
        this.purchases = purchases;
    }
    public Long getId() {
        return id;
    }
    public String getLocation() {
        return location;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
