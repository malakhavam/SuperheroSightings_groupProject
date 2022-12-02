package org.ss.controllers;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.ss.DAO.LocationDAO;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import org.ss.DTO.Location;
import org.ss.DTO.Super;
import org.ss.services.LocationServiceImpl;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */

@Controller
public class LocationController {
    
    private final LocationServiceImpl locationServiceImpl;
    public LocationController(LocationServiceImpl locationServiceImpl){
        this.locationServiceImpl = locationServiceImpl;
    }
    
    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    String latitudeError = null;
    String longitudeError = null;
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationServiceImpl.readAll();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    @GetMapping("/locations/addLocation")
    public String displayAddLocations(Model model) {
        model.addAttribute("errors", violations);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "/locations/addLocation";
    }
    
    @PostMapping("/locations/addLocation")
    public String addNewLocation(HttpServletRequest request, Model model){
        violations.clear();
        latitudeError = null;
        longitudeError = null;
        
        String name = request.getParameter("locationName");
        String stringLatitude = request.getParameter("latitude");
        String stringLongitude = request.getParameter("longitude");
        String description = request.getParameter("description");
        String address = request.getParameter("addressInformation");
        
        double latitude = 0;
        if(locationServiceImpl.isValidLatitude(stringLatitude)){
            latitude = Double.parseDouble(stringLatitude);
        } else {
            latitudeError = "Invalid or Empty Latitude";
        }
        
        double longitude = 0;
        if(locationServiceImpl.isValidLongitude(stringLongitude)){
            longitude = Double.parseDouble(stringLongitude);
        } else {
            longitudeError = "Invalid or Empty Longitude";
        }
        
        Location location = locationServiceImpl.createLocation(name, latitude, longitude, description, address);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);
        if(violations.isEmpty() && locationServiceImpl.isValidLatitude(stringLatitude) && locationServiceImpl.isValidLongitude(stringLongitude)){
            locationServiceImpl.create(location);
        }
        
        model.addAttribute("errors", violations);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "redirect:/locations/addLocation";
    }
    
    @GetMapping("/locations/deleteLocation")
    public String deleteLocation(Integer id) {
        locationServiceImpl.delete(id);
        return "redirect:/locations";
    }
    
    @GetMapping("/locations/editLocation")
    public String displayEditLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationServiceImpl.readByID(id);
        
        model.addAttribute("location", location);
        
        model.addAttribute("errors", violations);
        model.addAttribute("latitudeError", latitudeError);
        model.addAttribute("longitudeError", longitudeError);
        
        return "locations/editLocation";
    }
    
    @PostMapping("/locations/editLocation")
    public String editLocation(HttpServletRequest request, Model model){
        violations.clear();
        latitudeError = null;
        longitudeError = null;
        
        int id = Integer.parseInt(request.getParameter("locationId"));
        String name = request.getParameter("locationName");
        String stringLatitude = request.getParameter("latitude");
        String stringLongitude = request.getParameter("longitude");
        String description = request.getParameter("description");
        String address = request.getParameter("addressInformation");
        
        double latitude = 0;
        if(locationServiceImpl.isValidLatitude(stringLatitude)){
            latitude = Double.parseDouble(stringLatitude);
        } else {
            latitudeError = "Invalid or Empty Latitude";
        }
        
        double longitude = 0;
        if(locationServiceImpl.isValidLongitude(stringLongitude)){
            longitude = Double.parseDouble(stringLongitude);
        } else {
            longitudeError = "Invalid or Empty Longitude";
        }
        
        Location location = locationServiceImpl.createLocation(name, latitude, longitude, description, address);
        location.setLocationID(id);
        
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);
        if(violations.isEmpty() && locationServiceImpl.isValidLatitude(stringLatitude) && locationServiceImpl.isValidLongitude(stringLongitude)){
            locationServiceImpl.update(location);
            return "redirect:/locations";
        } else {
            model.addAttribute("location", locationServiceImpl.readByID(location.getLocationID()));
            model.addAttribute("errors", violations);
            model.addAttribute("latitudeError", latitudeError);
            model.addAttribute("longitudeError", longitudeError);
            return "locations/editLocation";
        }    
    }
    
    @GetMapping("locations/detailsLocation")
    public String displayDetailsLocation(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        
        Location location = locationServiceImpl.readByID(id);
        model.addAttribute("location", location);
        
        List<Super> supers = locationServiceImpl.getSupersByLocation(location);
        model.addAttribute("supers", supers);
        
        return "locations/detailsLocation";
    }
}
