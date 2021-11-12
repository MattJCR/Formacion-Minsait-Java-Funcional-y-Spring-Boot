package com.example.demo.beer;

import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<BeerEntity, Long> {

}
