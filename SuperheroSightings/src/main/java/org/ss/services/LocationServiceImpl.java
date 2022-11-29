package org.ss.services;

import org.ss.DAO.LocationDAO;
import org.ss.DTO.Location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationDAO locationDAO;

    @Override
    public Location readByID(int locationID) {
        return locationDAO.getLocationByID(locationID);
    }

    @Override
    public List<Location> readAll() {
        return locationDAO.getAllLocations();
    }

    @Override
    public List<Location> readLocationsBySuper(int superID) {
        return locationDAO.getLocationsBySuper(superID);
    }

    @Override
    public Location create(Location location) {
        return locationDAO.addNewLocation(location);
    }

    @Override
    public void update(Location location) {
        locationDAO.updateLocation(location);
    }

    @Override
    public void delete(int locationID) {
        locationDAO.deleteLocation(locationID);
    }

}
