package com.db.project.service;

import com.db.project.dto.graphDataDTO;
import com.db.project.model.AllData;
import com.db.project.model.Countries;
import com.db.project.model.Indicators;
import com.db.project.repository.AllDataRepository;
import com.db.project.repository.CountriesRepository;
import com.db.project.repository.IndicatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllDataServiceImpl implements AllDataService{
    @Autowired
    private AllDataRepository allDataRepository;
    @Autowired
    private CountriesRepository countriesRepository;
    @Autowired
    private IndicatorsRepository indicatorsRepository;

    @Override
    public List<Countries> getAllCountries() {
        return countriesRepository.findAll();
    }

    @Override
    public List<Indicators> getAllIndicators() {
        return indicatorsRepository.findAll();
    }

    @Override
    public List<Integer> getAllYears() {
        return allDataRepository.getAllYears();
    }

    @Override
    public List<graphDataDTO> getGraphData(Integer Country_Id, Integer Indicator_Id, Integer Year) {
        return allDataRepository.getGraphData(Country_Id,Indicator_Id,Year);
    }
}
