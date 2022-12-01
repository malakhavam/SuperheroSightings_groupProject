/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.services;

import org.ss.DAO.SuperDAO;
import org.ss.DAO.LocationDAO;
import org.ss.DAO.OrganizationDAO;
import org.ss.DAO.SightingDAO;
import org.ss.DAO.PowerDAO;
import org.ss.DTO.Super;
import org.ss.DTO.Organization;
import org.ss.DTO.Sighting;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
     @Autowired
    SuperDAO heroDao;
    
    @Autowired
    LocationDAO locationDao;
    
    @Autowired
    OrganizationDAO organizationDao;
    
    @Autowired
    SightingDAO sightingDao;
    
    @Autowired
    PowerDAO superpowerDao;
    
    public String buildUrl(HashMap<Sighting,Super> heroSightins){
        final String BASE_URL = "https://maps.googleapis.com/maps/api/staticmap?";
        // center=Brooklyn+Bridge,New+York,NY
        // &zoom=13
        // &size=600x300
        int height = 600;
        int length = 300;
        final String SIZE = "size=" + height + "x" + length;
        
        // &maptype=roadmap
        String mapType = "roadmap";
        final String MAP_TYPE = "maptype="+mapType;
        
        // &markers=color:blue%7Clabel:S%7C40.702147,-74.015794
        // &markers=color:green%7Clabel:G%7C40.711614,-74.012318
        // &markers=color:red%7Clabel:C%7C40.718217,-73.998284
        String markers = "";
        if(heroSightins != null){
            for(Sighting sighting : heroSightins.keySet()){
                markers += "&markers="
                        
                        + "label:"
                        + heroSightins.get(sighting).getName().charAt(0)
                        
                        + "%7C"
                        
                        + sighting.getSightingLocation().getLocationLatitude()
                        + ","
                        + sighting.getSightingLocation().getLocationLongitude();
            }
        }
        
        final String KEY = "key=" + getApiKey();
        
        final String url = BASE_URL 
                + SIZE
                + "&" + MAP_TYPE 
                + markers // & is already included
                + "&" + KEY;
        
        return url;
    }
    
    private String getApiKey(){
        final String API_KEY_FILE_PATH = "../API_KEY.txt";
        try{
            Scanner sc = new Scanner(new BufferedReader(new FileReader(API_KEY_FILE_PATH)));
            return sc.nextLine();
        } catch(Exception e){
            System.out.println("Error:"+e);
            return "";
        }
        
    }
    
    public int getNumberOfSuperheros(){
        int numberOfSuperheros = 0;
        List<Super> heros = heroDao.getAllSupers();
        for(Super hero : heros){
            if(hero.isIsSuper()){
                numberOfSuperheros++;
            }
        }
        return numberOfSuperheros;
    }
    
    public int getNumberOfSupervillains(){
        int numberOfSupervillains = 0;
        List<Super> heros = heroDao.getAllSupers();
        for(Super hero : heros){
            if(!hero.isIsSuper()){
                numberOfSupervillains++;
            }
        }
        return numberOfSupervillains;
    }
    
    public int getNumberOfSuperOrganization(){
        int numberOfSuperOrganization = 0;
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations){
            if(organization.isIsSuper()){
                numberOfSuperOrganization++;
            }
        }
        return numberOfSuperOrganization;
    }
    
    public int getNumberOfVillainOrganization(){
        int numberOfVillainOrganization = 0;
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations){
            if(!organization.isIsSuper()){
                numberOfVillainOrganization++;
            }
        }
        return numberOfVillainOrganization;
    }
    
    public int getNumberOfLocations(){
        return locationDao.getAllLocations().size();
    }
    
    public int getNumberOfSightings(){
        return sightingDao.getAllSightings().size();
    }
    
    public int getNumberOfSuperpowers(){
        return superpowerDao.getAllPowers().size();
    }
}
