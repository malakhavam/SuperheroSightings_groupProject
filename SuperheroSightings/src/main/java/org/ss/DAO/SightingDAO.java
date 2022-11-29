package org.ss.DAO;

import org.ss.DTO.Sighting;

import java.time.LocalDate;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface SightingDAO {
    public Sighting getSightingByID(int sightingID);

    public Sighting addNewSighting(Sighting sighting);

    public List<Sighting> getAllSightings();

    public void updateSighting(Sighting sighting);

    public void deleteSighting(int sightingID);

    public List<Sighting> getSightingsByDate(LocalDate date);
}
