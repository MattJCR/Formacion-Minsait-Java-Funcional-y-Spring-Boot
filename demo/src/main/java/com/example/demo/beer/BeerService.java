package com.example.demo.beer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BeerService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BeerRepository beerRepository;

    public void getBeerFromApi() {
        BeerResponse beerResponse[] = restTemplate.getForObject("https://api.punkapi.com/v2/beers",
                BeerResponse[].class);

        List<BeerEntity> beerEntityList = Arrays.asList(beerResponse).stream().map(b -> new BeerEntity(b.getName(),
                b.getTagline(), b.first_brewed, b.getDescription(), b.getimage_url(), b.getAbv())).toList();
        beerRepository.saveAll(beerEntityList);
    }

    public Iterable<BeerEntity> getAllBeers() {
        return beerRepository.findAll();
    }
}
