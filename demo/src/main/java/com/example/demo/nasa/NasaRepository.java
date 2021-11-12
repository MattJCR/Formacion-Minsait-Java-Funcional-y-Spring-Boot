package com.example.demo.nasa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface NasaRepository extends CrudRepository<Nasa, Long> {
    List<Nasa> findByTitle(String title);

    List<Nasa> findByDate(String date);
}
