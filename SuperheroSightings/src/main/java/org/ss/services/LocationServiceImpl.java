package org.ss.services;

import org.ss.DAO.LocationDAO;
import org.ss.DTO.Location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ss.DTO.Super;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class LocationServiceImpl implements LocationService{

    @Autowired
    LocationDAO locationDAO;

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

}
