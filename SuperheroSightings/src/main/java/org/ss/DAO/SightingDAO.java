/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.models.Sighting;
import org.ss.models.Location;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
public interface SightingDao {
    
    Sighting getSightingById(int id);
    
    List<Sighting> getAllSightings();
    
    Sighting addSighting(Sighting sighting);
    
    void updateSighting(Sighting sighting);
    
    void deleteSightingById(int id);
    
    List<Sighting> getSightingsForLocation(Location location);
}

