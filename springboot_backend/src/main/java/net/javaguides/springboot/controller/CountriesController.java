package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Countries;
import net.javaguides.springboot.service.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {
    @Autowired
    private CountriesService countriesService;

    @GetMapping()
    public List<Countries> getAllCountries(){
        return countriesService.getAllCountries();
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Countries> getCountry(@PathVariable("countryId") Integer countryId){
        return ResponseEntity.ok().body(countriesService.getCountry(countryId));
    }
}
