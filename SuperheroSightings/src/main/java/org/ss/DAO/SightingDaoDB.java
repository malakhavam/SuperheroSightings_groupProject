/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.models.Location;
import org.ss.models.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
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
public class SightingDaoDB implements SightingDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM Sightings WHERE SightingId = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), id);
            sighting.setLocation(getLocationForSighting(id));
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    private Location getLocationForSighting(int id){
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM Locations l "
                + "JOIN Sightings s ON s.LocationId = l.LocationId WHERE s.SightingId = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationDaoDB.LocationMapper(), id);
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM Sightings";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateLocationsForSightings(sightings);
        return sightings;
    }
    
    void associateLocationsForSightings(List<Sighting> sighthings){
        for (Sighting sighthing : sighthings) {
            sighthing.setLocation(getLocationForSighting(sighthing.getId()));
        }
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO Sightings(HeroId, LocationId, sightingDate) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getHeroId(),
                sighting.getLocation().getId(),
                sighting.getDate());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setId(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE Sightings SET HeroId = ?, LocationId = ?, sightingDate = ?"
                + "WHERE SightingId = ?";
        jdbc.update(UPDATE_SIGHTING,
                sighting.getHeroId(),
                sighting.getLocation().getId(),
                sighting.getDate(),
                sighting.getId());
    }

    @Override
    @Transactional
    public void deleteSightingById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM Sightings WHERE SightingId = ?";
        jdbc.update(DELETE_SIGHTING, id);
    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location) {
        final String SELECT_SIGHTINGS_FOR_LOCATION = "SELECT * FROM Sightings WHERE LocationId = ?";
        List<Sighting> sighting = jdbc.query(SELECT_SIGHTINGS_FOR_LOCATION, 
                new SightingMapper(), location.getId());
        associateLocationsForSightings(sighting);
        return sighting;
    }
    
    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("SightingId"));
            sighting.setHeroId(rs.getInt("HeroId"));
            sighting.setDate(Date.valueOf(rs.getDate("sightingDate").toString()));
            return sighting;
        }
    }
    
}
