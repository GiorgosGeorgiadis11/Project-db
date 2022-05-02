package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Countries;

import java.util.List;

public interface CountriesService {
    public List<Countries> getAllCountries();

    Countries getCountry(Integer countryId);
}
