package org.ss.services;

import org.ss.DAO.SightingDAO;
import org.ss.DTO.Location;
import org.ss.DTO.Sighting;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class SightingServiceImpl implements SightingService {

    @Autowired
    SightingDAO sightingDAO;

    @Override
    public Sighting create(Sighting sighting) {
        return sightingDAO.addNewSighting(sighting);
    }

    @Override
    public List<Sighting> readAll() {
        return sightingDAO.getAllSightings();
    }

    @Override
    public Sighting readByID(int sightingID) {
        return sightingDAO.getSightingByID(sightingID);
    }

    @Override
    public List<Sighting> readSightingsByDate(Location location) {
        return sightingDAO.getSightingsForLocation(location);
    }

    @Override
    public void update(Sighting sighting) {
        sightingDAO.updateSighting(sighting);
    }

    @Override
    public void delete(int sightingID) {
        sightingDAO.deleteSighting(sightingID);
    }


}
