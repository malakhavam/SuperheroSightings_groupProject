/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.models.Hero;
import org.ss.models.Location;
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
public class LocationDaoDB implements LocationDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int id) {
        try {
            final String SELECT_LOCATION_BY_ID = "SELECT * FROM Locations WHERE LocationId = ?";
            return jdbc.queryForObject(SELECT_LOCATION_BY_ID, new LocationMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM Locations";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION = "INSERT INTO Locations(locationName, locationLatitude, locationLongitude, locationDescription, locationAddress) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getDescription(),
                location.getAddressInformation());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION = "UPDATE Locations SET locationName = ?, locationLatitude = ?, locationLongitude = ?, locationDescription = ?, locationAddress = ?"
                + "WHERE LocationId = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getLatitude(),
                location.getLongitude(),
                location.getDescription(),
                location.getAddressInformation(),
                location.getId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {
        final String DELETE_SIGHTING = "DELETE FROM Sightings WHERE LocationId = ?";
        jdbc.update(DELETE_SIGHTING, id);
        
        final String DELETE_LOCATION = "DELETE FROM Locations WHERE LocationId = ?";
        jdbc.update(DELETE_LOCATION, id);
    }

    @Override
    public List<Location> getLocationsForHero(Hero hero) {
        final String SELECT_LOCATIONS_FOR_HERO = "SELECT l.* FROM Locations l "
                + "JOIN Sightings s ON s.LocationId = l.LocationId "
                + "WHERE s.HeroId = ?";
        List<Location> locations = jdbc.query(SELECT_LOCATIONS_FOR_HERO, 
                new LocationMapper(), hero.getId());
        return locations;
    }
    
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("LocationId"));
            location.setName(rs.getString("locationName"));
            location.setLatitude(rs.getDouble("locationLatitude"));
            location.setLongitude(rs.getDouble("locationLongitude"));
            location.setDescription(rs.getString("locationDescription"));
            location.setAddressInformation(rs.getString("locationAddress"));
            return location;
        }
    }    
}
