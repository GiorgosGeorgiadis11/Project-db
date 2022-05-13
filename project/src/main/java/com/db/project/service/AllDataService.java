package com.db.project.service;

import com.db.project.dto.graphDataDTO;
import com.db.project.model.Countries;
import com.db.project.model.Indicators;

import java.util.List;

public interface AllDataService {
    public List<Countries> getAllCountries();

    public List<Indicators> getAllIndicators();

    public List<Integer> getAllYears();

    public List<graphDataDTO> getGraphData(Integer Country_Id,Integer Indicator_Id,Integer Year);
}
