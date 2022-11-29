package org.ss.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.ss.DAO.LocationDAO;
import org.ss.DAO.OrganizationDAO;
import org.ss.DAO.SuperDAO;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class SuperController {

    private final SuperDAO superDao;

    public SuperController(SuperDAO superDao) {
        this.superDao = superDao;
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
