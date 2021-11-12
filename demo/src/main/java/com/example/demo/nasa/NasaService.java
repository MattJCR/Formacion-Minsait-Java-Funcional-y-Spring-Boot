package com.example.demo.nasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NasaService {
    @Autowired
    private NasaRepository nasaRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Nasa findNasaItem(String id) {
        System.out.println(id);
        Nasa nasaItem = nasaRepository.findById(Long.parseLong(id)).orElse(null);
        System.out.println(nasaItem);
        return nasaItem;
    }

    public Iterable<Nasa> findAllNasaItems() {
        Iterable<Nasa> nasaItems = nasaRepository.findAll();
        System.out.println(nasaItems);
        return nasaItems;
    }

    public Nasa findNasaItemByDate(String date) {
        NasaResponse nasaResponse = restTemplate
                .getForObject("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&date=" + date, NasaResponse.class);
        Nasa nasaItem = new Nasa(nasaResponse.getDate(), nasaResponse.getExplanation(), nasaResponse.getTitle(),
                nasaResponse.getUrl(), nasaResponse.getMediaType());
        System.out.println(nasaItem);
        return nasaItem;
    }
}
