package com.example.demo.beer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<BeerEntity, Long> {
    List<BeerEntity> findByAbvLessThanEqual(Double abv);

    List<BeerEntity> findByAbvGreaterThanEqual(Double abv);

    List<BeerEntity> findByAbvLessThanEqualAndAbvGreaterThanEqual(Double less, Double great);
}
