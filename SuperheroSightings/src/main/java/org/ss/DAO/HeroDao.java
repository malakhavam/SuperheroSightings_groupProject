/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.models.Hero;
import org.ss.models.Location;
import org.ss.models.Sighting;
import org.ss.models.Superpower;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
public interface HeroDao {
    
    Hero getHeroById(int id);
    
    List<Hero> getAllHeroes();
    
    Hero addHero(Hero hero);
    
    void updateHero(Hero hero);
    
    void deleteHeroById(int id);
    
    List<Hero> getHeroesForSuperpower(Superpower superpower);
    
    Hero getHeroForSighting(Sighting sighting);
    
    List<Hero> getHeroesForLocation(Location location);
}
