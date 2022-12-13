/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.models.Location;
import org.ss.models.Hero;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
public interface LocationDao {
    
    Location getLocationById(int id);
    
    List<Location> getAllLocations();
    
    Location addLocation(Location location);
    
    void updateLocation(Location location);
    
    void deleteLocationById(int id);
    
    List<Location> getLocationsForHero(Hero hero);
}
