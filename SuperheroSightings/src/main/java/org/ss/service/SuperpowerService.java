/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.service;

import org.ss.dao.HeroDao;
import org.ss.dao.LocationDao;
import org.ss.dao.OrganizationDao;
import org.ss.dao.SightingDao;
import org.ss.dao.SuperpowerDao;
import org.ss.models.Hero;
import org.ss.models.Superpower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
@Service
public class SuperpowerService {
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    // SERVICE FUNCTION
    public Superpower createSuperpower(String name, String description){
        Superpower superpower = new Superpower();
        superpower.setName(name);
        superpower.setDescription(description);

        return superpower;
    }
    
    // EXTERNAL DAO FUNCTIONS  
    public List<Hero> getHeroesForSuperpower(Superpower superpower){
        return heroDao.getHeroesForSuperpower(superpower);
    }
    // LOCAL DAO FUNCTIONS  
    public Superpower getSuperpowerById(int id){
        return superpowerDao.getSuperpowerById(id);      
    }
    public List<Superpower> getAllSuperpowers(){
        return superpowerDao.getAllSuperpowers();
    }
    public Superpower addSuperpower(Superpower superpower){
        return superpowerDao.addSuperpower(superpower);
    }
    public void updateSuperpower(Superpower superpower){
        superpowerDao.updateSuperpower(superpower);
    }
    public void deleteSuperpowerById(int id){
        superpowerDao.deleteSuperpowerById(id);
    }
}
