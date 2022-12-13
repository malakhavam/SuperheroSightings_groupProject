/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.dao.SightingDaoDB.SightingMapper;
import org.ss.dao.SuperpowerDaoDB.SuperpowerMapper;
import org.ss.models.Hero;
import org.ss.models.Location;
import org.ss.models.Sighting;
import org.ss.models.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
@Repository
public class HeroDaoDB implements HeroDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    SightingDaoDB sightingDaoDB;

    @Override
    public Hero getHeroById(int id) {
        try {
            final String SELECT_HERO_BY_ID = "SELECT * FROM Heroes WHERE HeroId = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroMapper(), id);
            hero.setSuperpowers(getSuperpowersForHero(id));
            hero.setSightings(getSightingsForHero(id));
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    private List<Superpower> getSuperpowersForHero(int id) {
        final String SELECT_SUPERPOWERS_FOR_HERO = "SELECT s. * FROM Superpowers s "
                + "JOIN HeroSuperpowers hs ON hs.SuperpowerId = s.SuperpowerId WHERE hs.HeroId = ?";
        return jdbc.query(SELECT_SUPERPOWERS_FOR_HERO, new SuperpowerMapper(), id);
    }
    
    private List<Sighting> getSightingsForHero(int id) {
        final String SELECT_SIGHTINGS_FOR_HERO = "SELECT * FROM Sightings WHERE HeroId = ?";
        List<Sighting> sighthings = jdbc.query(SELECT_SIGHTINGS_FOR_HERO, new SightingMapper(), id);
        sightingDaoDB.associateLocationsForSightings(sighthings);
        return sighthings;
    }

    @Override
    public List<Hero> getAllHeroes() {
        final String SELECT_ALL_HEROES = "SELECT * FROM Heroes";
        List<Hero> heroes = jdbc.query(SELECT_ALL_HEROES, new HeroMapper());
        associateSuperpowersAndSightings(heroes);
        return heroes;
    }
    
    public void associateSuperpowersAndSightings(List<Hero> heroes) {
        for (Hero hero : heroes) {
            hero.setSuperpowers(getSuperpowersForHero(hero.getId()));
            hero.setSightings(getSightingsForHero(hero.getId()));
        }
    }

    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO Heroes(IsHero, heroName, heroDescription) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_HERO,
                hero.isIsHero(),
                hero.getName(),
                hero.getDescription());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setId(newId);
        insertHeroSuperpower(hero);
        insertSighting(hero);
        return hero;
    }
    
    private void insertHeroSuperpower(Hero hero){
        final String INSERT_HERO_SUPERPOWER = "INSERT INTO "
                + "HeroSuperpowers(HeroId, SuperpowerId) VALUES(?,?)";
        for(Superpower superpower : hero.getSuperpowers()) {
            jdbc.update(INSERT_HERO_SUPERPOWER, 
                    hero.getId(),
                    superpower.getId());
        }
    }
    
    private void insertSighting(Hero hero){
        final String INSERT_SIGHTING = "INSERT INTO "
                + "Sightings(HeroId, LocationId, sightingDate) VALUES(?,?,?)";
        for(Sighting sighting : hero.getSightings()){
            jdbc.update(INSERT_SIGHTING,
                    hero.getId(),
                    sighting.getLocation().getId(),
                    sighting.getDate());
            int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            sighting.setId(newId);
        }
    }

    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE Heroes SET IsHero = ?, heroName = ?, heroDescription = ?"
                + "WHERE HeroId = ?";
        jdbc.update(UPDATE_HERO,
                hero.isIsHero(),
                hero.getName(),
                hero.getDescription(),
                hero.getId());
        
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpowers WHERE HeroId = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, hero.getId());
        insertHeroSuperpower(hero);
        
        final String DELETE_SIGHTING = "DELETE FROM Sightings WHERE HeroId = ?";
        jdbc.update(DELETE_SIGHTING, hero.getId());
        insertSighting(hero);
    }

    @Override
    @Transactional
    public void deleteHeroById(int id) {
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpowers WHERE HeroId = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, id);
        
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganizations WHERE HeroId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, id);
        
        final String DELETE_HEROESIGHTING = "DELETE FROM Sightings WHERE HeroId = ?";
        jdbc.update(DELETE_HEROESIGHTING, id);
        
        final String DELETE_HERO = "DELETE FROM Heroes WHERE HeroId = ?";
        jdbc.update(DELETE_HERO, id);
    }

    @Override
    public List<Hero> getHeroesForSuperpower(Superpower superpower) {
        final String SELECT_HEROES_FOR_SUPERPOWER = "SELECT h.* FROM Heroes h JOIN "
                + "HeroSuperpowers hs ON hs.HeroId = h.HeroId WHERE hs.SuperpowerId = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_SUPERPOWER, 
                new HeroMapper(), superpower.getId());
        associateSuperpowersAndSightings(heroes);
        return heroes;
    }

    @Override
    public Hero getHeroForSighting(Sighting sighting) {
        final String SELECT_HEROES_FOR_SIGHTING = "SELECT h.* FROM Heroes h JOIN "
                + "Sightings s ON s.HeroId = h.HeroId WHERE s.SightingId = ?";
        Hero hero = jdbc.queryForObject(SELECT_HEROES_FOR_SIGHTING, 
                new HeroMapper(), sighting.getId());
        hero.setSuperpowers(getSuperpowersForHero(hero.getId()));
        hero.setSightings(getSightingsForHero(hero.getId()));
        return hero;
    }

    @Override
    public List<Hero> getHeroesForLocation(Location location) {
        final String SELECT_HEROES_FOR_LOCATION = "SELECT h.* FROM Heroes h "
                + "JOIN Sightings s ON s.HeroId = h.HeroId "
                + "WHERE s.LocationId = ?";
        List<Hero> heroes = jdbc.query(SELECT_HEROES_FOR_LOCATION, 
                new HeroMapper(), location.getId());
        associateSuperpowersAndSightings(heroes);
        return heroes;
    }
    
    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("HeroId"));
            hero.setIsHero(rs.getBoolean("IsHero"));
            hero.setName(rs.getString("heroName"));
            hero.setDescription(rs.getString("heroDescription"));
            return hero;
        }
    }
    
}
