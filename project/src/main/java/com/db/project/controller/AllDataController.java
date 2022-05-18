package com.db.project.controller;

import com.db.project.dto.graphDataDTO;
import com.db.project.model.Countries;
import com.db.project.model.Indicators;
import com.db.project.model.PlotObject;
import com.db.project.service.AllDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;


@Controller
public class AllDataController {
    private int num;
    @Autowired
    private AllDataService allDataService;

    @RequestMapping("/graph")
    public ModelAndView graphPage(Model model){
        List<Countries> countries = allDataService.getAllCountries();
        List<Indicators> indicators = allDataService.getAllIndicators();
        List<Integer> Year = allDataService.getAllYears();
        List<Integer> countries_Id = new ArrayList<Integer>();
        List<Integer> Indicators_Id = new ArrayList<Integer>();
        PlotObject tdata = new PlotObject(countries_Id,Indicators_Id,0,0,"");
        model.addAttribute("obj",tdata);
        model.addAttribute("countries",countries);
        model.addAttribute("indicators",indicators);
        model.addAttribute("Year",Year);
        return new ModelAndView("graph", (Map<String, ?>) model);
    }

    @PostMapping(value = "/viewGraph")
    @ResponseBody
    public ModelAndView graphData(@ModelAttribute("obj") PlotObject data,Model model){
        num = 0;
        List<String> countriesName = new ArrayList<String>();
        int countryLen = data.getCountries_Id().size();
        int indicatorLen = data.getIndicators_Id().size();
        for(int i = 0;i<countryLen;i++){
            List<Integer> country = Arrays.asList(data.getCountries_Id().get(i));
            List<Double> graphData = allDataService.getGraphData(country,data.getIndicators_Id(),data.getYearBefore(),data.getYearAfter());
            model.addAttribute("country"+num(),graphData);
        }
        model.addAttribute("countryLen",countryLen);
        model.addAttribute("pointSYear",data.getYearBefore());
        //List<graphDataDTO> graphData = allDataService.getGraphData(data.getCountries_Id(),data.getIndicators_Id(),data.getYearBefore(),data.getYearAfter());
        //model.addAttribute("graphData"+num(),graphData);
        //model.addAttribute("countryList",countryList);
        return new ModelAndView("viewGraph", (Map<String, ?>) model);
    }
    public String num(){
        num = num + 1;
        return String.valueOf(num);
    }
}
