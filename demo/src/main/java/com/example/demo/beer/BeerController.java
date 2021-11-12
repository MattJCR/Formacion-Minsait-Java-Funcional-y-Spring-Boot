package com.example.demo.beer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BeerController {
    @Autowired
    private BeerService beerService;

    @GetMapping("/beer")
    public String getNasaItem(@RequestParam(name = "min", required = false) String min,
            @RequestParam(name = "max", required = false) String max, Model model) {
        System.out.println("Min: " + min + " - " + "Max: " + max);
        model.addAttribute("beers", beerService.getAllBeers());
        return "beers";
    }
}
