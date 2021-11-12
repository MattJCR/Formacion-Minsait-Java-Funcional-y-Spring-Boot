package com.example.demo.nasa;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NasaController {
    @Autowired
    private NasaService nasaService;

    @GetMapping("/nasa")
    public String getNasaItem(@RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "date", required = false) String date, Model model) {
        System.out.println("Controller_id: " + id);
        System.out.println("Controller_date: " + date);
        if (Objects.nonNull(id)) {
            model.addAttribute("nasaItem", nasaService.findNasaItem(id));
            return "nasa";
        } else if (Objects.nonNull(date)) {
            model.addAttribute("nasaItem", nasaService.findNasaItemByDate(date));
            return "nasa";
        }
        model.addAttribute("nasaItems", nasaService.findAllNasaItems());
        return "nasaall";
    }

}
