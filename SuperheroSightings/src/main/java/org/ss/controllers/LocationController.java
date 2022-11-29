package org.ss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ss.DAO.LocationDAO;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;

/**
*@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */

@RestController
@RequestMapping("/api")
public class LocationController {

    
    private final LocationDAO locationDao;

    public LocationController(LocationDAO locationDao) {
        this.locationDao = locationDao;
    }

    

    /*
    @PostMapping("/addLocation")
    public String addLocation() {

    }

    @GetMapping("/locations")
    public String displayLocations() {

    }

    @GetMapping("/editLocation")
    public String editLocation() {

    }

    @PostMapping("/editLocation")
    public String performEditLocation() {

    }

    @GetMapping("/deleteLocation")
    public String deleteLocation() {

    }
    */
}
