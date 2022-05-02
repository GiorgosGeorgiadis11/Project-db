package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Countries;
import net.javaguides.springboot.repository.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CountriesServiceImpl implements CountriesService{

    @Autowired
    private CountriesRepository countriesRepository;

    public List<Countries> getAllCountries(){

        return countriesRepository.findAll();
    }

    @Override
    public Countries getCountry(Integer countryId) {
        return countriesRepository.findById(countryId).get();
    }
}
