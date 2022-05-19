package com.db.project.controller;

import com.db.project.model.Countries;
import com.db.project.model.Indicators;
import com.db.project.model.PlotObject;
import com.db.project.service.AllDataService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


@Controller
public class AllDataController {
    private int num;
    @Autowired
    private AllDataService allDataService;

    @RequestMapping("/graph")
    public ModelAndView graphPage(ModelMap model){
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
        return new ModelAndView("graph", model);
    }
    @RequestMapping("/barChart")
    public ModelAndView barChart(@ModelAttribute("obj") PlotObject data,ModelMap model){
        num = 0;
        List<String> countriesName = new ArrayList<String>();
        int countryLen = data.getCountries_Id().size();
        int indicatorLen = data.getIndicators_Id().size();

        JSONObject dataValues = new JSONObject();
        JSONObject countryNames = new JSONObject();
        JSONObject indicatorNames = new JSONObject();

        int dataCounter = 0;
        for(int indicatorsCounter = 0;  indicatorsCounter<indicatorLen;   indicatorsCounter++) {
            for (int countriesCounter = 0; countriesCounter < countryLen; countriesCounter++) {
                List<Integer> country = Arrays.asList(data.getCountries_Id().get(countriesCounter));
                List<Integer> indicator = Arrays.asList(data.getIndicators_Id().get(indicatorsCounter));
                List<Double> graphData = allDataService.getGraphData(country, indicator, data.getYearBefore(), data.getYearAfter());

                dataValues.put(dataCounter + "", graphData.toArray());
                countryNames.put(countriesCounter + "", allDataService.getCountryNameById(data.getCountries_Id().get(countriesCounter)));
                indicatorNames.put(indicatorsCounter+ "",allDataService.getIndicatorNameById(data.getIndicators_Id().get(indicatorsCounter)));

                dataCounter+=1;
            }
        }
        Iterator x = dataValues.keys();
        JSONArray dataValuesArray = new JSONArray();

        Iterator y = countryNames.keys();
        JSONArray countryNamesArray = new JSONArray();

        Iterator z = indicatorNames.keys();
        JSONArray indicatorNamesArray = new JSONArray();

        while (x.hasNext()){
            String key = (String) x.next();
            dataValuesArray.put(dataValues.get(key));
        }

        while (y.hasNext()){
            String key = (String) y.next();
            countryNamesArray.put(countryNames.get(key));
        }

        while (z.hasNext()){
            String key = (String) z.next();
            indicatorNamesArray.put(indicatorNames.get(key));
        }

        model.addAttribute("dataValues",dataValuesArray);
        model.addAttribute("countryNames",countryNamesArray);
        model.addAttribute("indicatorNames",indicatorNamesArray);

        model.addAttribute("countryLen",countryLen);
        model.addAttribute("indicatorLen",indicatorLen);
        model.addAttribute("yearBefore",data.getYearBefore());
        model.addAttribute("yearAfter",data.getYearAfter());

        return new ModelAndView("barChart", model);
    }


    @PostMapping(value = "/lineChart")
    @ResponseBody
    public ModelAndView graphData(@ModelAttribute("obj") PlotObject data,ModelMap model){
        num = 0;
        List<String> countriesName = new ArrayList<String>();
        int countryLen = data.getCountries_Id().size();
        int indicatorLen = data.getIndicators_Id().size();

        JSONObject dataValues = new JSONObject();
        JSONObject countryNames = new JSONObject();
        JSONObject indicatorNames = new JSONObject();

        int dataCounter = 0;
        for(int indicatorsCounter = 0;  indicatorsCounter<indicatorLen;   indicatorsCounter++) {
            for (int countriesCounter = 0; countriesCounter < countryLen; countriesCounter++) {
                List<Integer> country = Arrays.asList(data.getCountries_Id().get(countriesCounter));
                List<Integer> indicator = Arrays.asList(data.getIndicators_Id().get(indicatorsCounter));

                List<Double> graphData = allDataService.getGraphData(country, indicator, data.getYearBefore(), data.getYearAfter());

                dataValues.put(dataCounter + "", graphData.toArray());
                countryNames.put(countriesCounter + "", allDataService.getCountryNameById(data.getCountries_Id().get(countriesCounter)));
                indicatorNames.put(indicatorsCounter+ "",allDataService.getIndicatorNameById(data.getIndicators_Id().get(indicatorsCounter)));

                dataCounter+=1;
            }
        }
        Iterator x = dataValues.keys();
        JSONArray dataValuesArray = new JSONArray();

        Iterator y = countryNames.keys();
        JSONArray countryNamesArray = new JSONArray();

        Iterator z = indicatorNames.keys();
        JSONArray indicatorNamesArray = new JSONArray();

        while (x.hasNext()){
            String key = (String) x.next();
            dataValuesArray.put(dataValues.get(key));
        }

        while (y.hasNext()){
            String key = (String) y.next();
            countryNamesArray.put(countryNames.get(key));
        }

        while (z.hasNext()){
            String key = (String) z.next();
            indicatorNamesArray.put(indicatorNames.get(key));
        }

        model.addAttribute("dataValues",dataValuesArray);
        model.addAttribute("countryNames",countryNamesArray);
        model.addAttribute("indicatorNames",indicatorNamesArray);

        model.addAttribute("countryLen",countryLen);
        model.addAttribute("indicatorLen",indicatorLen);
        model.addAttribute("pointSYear",data.getYearBefore());

        return new ModelAndView("lineChart", model);
    }
    public String num(){
        num = num + 1;
        return String.valueOf(num);
    }
}
