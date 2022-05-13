package com.db.project.controller;

import com.db.project.dto.graphDataDTO;
import com.db.project.model.AllData;
import com.db.project.model.Countries;
import com.db.project.model.Indicators;
import com.db.project.service.AllDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
public class AllDataController {
    @Autowired
    private AllDataService allDataService;

    @RequestMapping("/graph")
    public ModelAndView graphPage(Model model){
        List<Countries> countries = allDataService.getAllCountries();
        List<Indicators> indicators = allDataService.getAllIndicators();
        List<Integer> Year = allDataService.getAllYears();
        AllData tdata = new AllData(0,0,0,"");
        model.addAttribute("obj",tdata);
        model.addAttribute("countries",countries);
        model.addAttribute("indicators",indicators);
        model.addAttribute("Year",Year);
        return new ModelAndView("graph", (Map<String, ?>) model);
    }

    @PostMapping(value = "/viewGraph")
    @ResponseBody
    public ModelAndView graphData(@ModelAttribute("obj") AllData data,Model model){
        List<graphDataDTO> graphData = allDataService.getGraphData(data.getCountry_Id(),data.getIndicator_Id(),data.getYear());
        model.addAttribute("graphData",graphData);
        return new ModelAndView("viewGraph", (Map<String, ?>) model);
    }


}
