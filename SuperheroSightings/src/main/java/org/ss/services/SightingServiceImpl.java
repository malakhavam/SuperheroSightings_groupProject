package org.ss.services;

import org.ss.DAO.SightingDAO;
import org.ss.DAO.SuperDAO;
import org.ss.DTO.Location;
import org.ss.DTO.Sighting;
import org.ss.DTO.Super;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */

@Service
public class SightingServiceImpl implements SightingService {

    @Autowired
    SightingDAO sightingDAO;
    
    @Autowired
    SuperDAO superDAO;
    
    // 2 functions to get last sightings and the superes seen
    public List<Sighting> getLastSightings(int number){
        List<Sighting> sightings = sightingDAO.getAllSightings();
        Collections.sort(sightings, new SortByDateDesc());
        if(number >= sightings.size()){
            return sightings;
        } else {
            List<Sighting> result = new ArrayList<>();
            for(int i=0;i<sightings.size();i++){
                result.add(sightings.get(i));
            }
            return result;
        }
    }
    
    public HashMap<Sighting, Super> mapSuperSightings(List<Sighting> sightings){
        HashMap<Sighting, Super> superSightings = new HashMap<>();
        for(int i=0;i<sightings.size();i++){
            superSightings.put(sightings.get(i), getSuperForSighting(sightings.get(i)));
        }
        return superSightings;
    }
    
    
    public static final class SortByDateDesc implements Comparator<Sighting>{

        @Override
        public int compare(Sighting s1, Sighting s2) {
            return s2.getSightingDate().compareTo(s1.getSightingDate());
        }
        
    }
    
    // EXTERNAL DAO FUNCTIONS  
    public Super getSuperForSighting(Sighting sighting){
        return superDAO.getSuperForSighting(sighting);
    }
   /* 
    // LOCAL DAO FUNCTIONS  
    public Sighting getSightingById(int id){
        return sightingDAO.getSightingById(id);      
    }
    public List<Sighting> getAllSightings(){
        return sightingDAO.getAllSightings();
    }
    public Sighting addSighting(Sighting sighting){
        return sightingDAO.addSighting(sighting);
    }
    public void updateSighting(Sighting sighting){
        sightingDAO.updateSighting(sighting);
    }
    public void deleteSightingById(int id){
        sightingDAO.deleteSightingById(id);
    }
*/
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
