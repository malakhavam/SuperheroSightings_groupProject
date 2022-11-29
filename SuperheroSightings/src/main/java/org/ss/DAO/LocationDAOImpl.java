package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.ss.DTO.Location;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */

@Repository
public class LocationDAOImpl implements LocationDAO {

    private final JdbcTemplate jdbc;

    @Autowired
    public LocationDAOImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Location getLocationByID(int locationID) {
        try {
            final String SQL = "SELECT * FROM Locations WHERE locationID = ?";
            return jdbc.queryForObject(SQL, new LocationMapper(), locationID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    //@Transactional
    public Location addNewLocation(Location location) {
        final String SQL = "INSERT INTO Locations(locationID, locationName, locationDescription, locationAddress, locationLatitude, locationLongitude)" +
            "VALUES(?,?,?,?,?,?);";
            jdbc.update(SQL,
                    location.getLocationID(),
            location.getLocationName(),
            location.getLocationDescription(),
            location.getLocationAddress(),
            location.getLocationLatitude(),
            location.getLocationLongitude());

            int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            location.setLocationID(newID);
            return location;
    }

    @Override
    public List<Location> getAllLocations() {
        final String SQL = "SELECT * FROM Locations";
        return jdbc.query(SQL, new LocationMapper());
    }

    @Override
    public void updateLocation(Location location) {
        final String SQL = "UPDATE Locations SET locationName = ?, locationDescription = ?, locationAddress = ?, locationLatitude = ?, locationLongitude = ? WHERE locationID = ?";
        jdbc.update(SQL, 
            location.getLocationName(),
            location.getLocationDescription(),
            location.getLocationAddress(),
            location.getLocationLatitude(),
            location.getLocationLongitude());
    }

    @Override
    //@Transactional
    public void deleteLocation(int locationID) {
        final String DELETE_LOCATION = "DELETE FROM Locations WHERE locationID =?";
        jdbc.update(DELETE_LOCATION, locationID);

        final String DELETE_SIGHTING = "DELETE FROM Sightings WHERE locationID = ?";
        jdbc.update(DELETE_SIGHTING, locationID);
    }

    @Override
    public List<Location> getLocationsBySuper(int superID) {
        final String SQL = "SELECT Locations.locationID, Locations.locationName, Locations.locationDescription, Locations.locationAddress, Locations.locationLatitude, Locations.locationLongitude"
        + "FROM Sightings"
        + "JOIN Location ON Sightings.locationID = Locations.locationID"
        + "WHERE Sightings.superID = ?";
        return jdbc.query(SQL, new LocationMapper(), superID);
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location location = new Location();
            location.setLocationID(rs.getInt("locationID"));
            location.setLocationName(rs.getString("locationName"));
            location.setLocationDescription(rs.getString("locationDescription"));
            location.setLocationAddress(rs.getString("locationAddress"));
            location.setLocationLatitude(rs.getString("locationLatitude"));
            location.setLocationLongitude(rs.getString("locationLongitude"));
            return location;
        }
    }


}
