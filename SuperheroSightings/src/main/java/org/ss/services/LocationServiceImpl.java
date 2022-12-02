package org.ss.services;

import org.ss.DAO.LocationDAO;
import org.ss.DTO.Location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ss.DAO.OrganizationDAO;
import org.ss.DAO.PowerDAO;
import org.ss.DAO.SightingDAO;
import org.ss.DAO.SuperDAO;
import org.ss.DTO.Super;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationDAO locationDAO;
    
    @Autowired
    SuperDAO superDao;
    
    @Autowired
    OrganizationDAO organizationDao;
    
    @Autowired
    SightingDAO sightingDao;
    
    @Autowired
    PowerDAO powerDao;
    
    // SERVICE FUNCTION
    public Location createLocation(String name, double latitude, double longitude, String description, String address){
        Location location = new Location();
        location.setLocationName(name);
        location.setLocationLatitude(latitude);
        location.setLocationLongitude(longitude);
        location.setLocationDescription(description);
        location.setLocationAddress(address);
        
        return location;
    }

    public boolean isValidLatitude(String latitude){
        try{
            double value = Double.parseDouble(latitude);
            if(value<-90 || value>90){
                return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    public boolean isValidLongitude(String longitude){
        try{
            double value = Double.parseDouble(longitude);
            if(value<-180 || value>180){
                return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    @Override
    public Location create(Location location) {
        return locationDAO.addNewLocation(location);
    }

    @Override
    public List<Location> readAll() {
        return locationDAO.getAllLocations();
    }

    @Override
    public Location readByID(int locationID) {
        return locationDAO.getLocationByID(locationID);
    }

    @Override
    public List<Location> readLocationsBySuper(Super sup) {
        return locationDAO.getLocationsBySuper(sup);
    }

    @Override
    public void update(Location location) {
        locationDAO.updateLocation(location);
    }

    @Override
    public void delete(int locationID) {
        locationDAO.deleteLocation(locationID);
    }

    
    public List<Super> getSupersByLocation(Location location){
        return superDao.getSupersByLocation(location);
    }
    
     public Location addLocation(Location location){
        return locationDAO.addNewLocation(location);
    }
    

   

}
