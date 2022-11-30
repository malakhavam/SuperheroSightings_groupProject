package org.ss.services;

import org.ss.DTO.Location;
import org.ss.DTO.Sighting;

import java.time.LocalDate;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface SightingService {

    public Sighting create(Sighting sighting);

    public List<Sighting> readAll();

    public Sighting readByID(int sightingID);

    public List<Sighting> readSightingsByDate(Location location);

    public void update(Sighting sighting);

    public void delete(int sightingID);
}
