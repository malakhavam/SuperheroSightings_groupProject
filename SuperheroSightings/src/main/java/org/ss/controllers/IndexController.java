package org.ss.controllers;

import org.ss.services.SightingServiceImpl;
import org.ss.services.IndexService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import org.ss.DTO.Sighting;
import org.ss.DTO.Super;

@Controller
public class IndexController {
        
    private final SightingServiceImpl sightingService;
    private final IndexService indexService;
    public IndexController(SightingServiceImpl sightingService, IndexService indexService){
        this.sightingService = sightingService;
        this.indexService = indexService;
    }
    
    private String googleMapUrl;
    private ArrayList<String> insightsList;
    private HashMap<String,Integer> insights;
    
        
    @GetMapping("/")
    public String displayIndex(Model model) {
        final int SIGHTINGS_PER_PAGE = 10;
        List<Sighting> sightings = sightingService.getLastSightings(SIGHTINGS_PER_PAGE);
        HashMap<Sighting,Super> superSightings = sightingService.mapSuperSightings(sightings);
        model.addAttribute("sightings", sightings);
        model.addAttribute("superSightings", superSightings);
        
        googleMapUrl = indexService.buildUrl(superSightings);
        
        model.addAttribute("googleMapUrl", googleMapUrl);
        
        insights = new HashMap<>();
        insightsList = new ArrayList<>();
        
        final String NUMBER_SUPERHEROS = "Number of Superheroes";
        final String NUMBER_SUPERVILLAIN = "Number of Supervillains";
        final String NUMBER_HERO_ORGANIZATION = "Number of Hero Organizations";
        final String NUMBER_VILLAIN_ORGANIZATION = "Number of Villain Organizations";
        final String NUMBER_LOCATIONS = "Number of Locations";
        final String NUMBER_SIGHTINGS = "Number of Sightings";
        final String NUMBER_SUPERPOWER = "Number of Superpowers";
        
        insights.put(NUMBER_SUPERHEROS,indexService.getNumberOfSuperheros());
        insightsList.add(NUMBER_SUPERHEROS);
        
        insights.put(NUMBER_SUPERVILLAIN,indexService.getNumberOfSupervillains());
        insightsList.add(NUMBER_SUPERVILLAIN);
        
        insights.put(NUMBER_HERO_ORGANIZATION,indexService.getNumberOfSuperOrganization());
        insightsList.add(NUMBER_HERO_ORGANIZATION);
        
        insights.put(NUMBER_VILLAIN_ORGANIZATION,indexService.getNumberOfVillainOrganization());
        insightsList.add(NUMBER_VILLAIN_ORGANIZATION);
        
        insights.put(NUMBER_LOCATIONS,indexService.getNumberOfLocations());
        insightsList.add(NUMBER_LOCATIONS);
        
        insights.put(NUMBER_SIGHTINGS,indexService.getNumberOfSightings());
        insightsList.add(NUMBER_SIGHTINGS);
        
        insights.put(NUMBER_SUPERPOWER,indexService.getNumberOfSuperpowers());
        insightsList.add(NUMBER_SUPERPOWER);    
        
        model.addAttribute("insightsList",insightsList);
        model.addAttribute("insights",insights);
        
        
        return "index";
    }


}
