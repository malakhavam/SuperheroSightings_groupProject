package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.ss.DTO.Location;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class LocationDAOImpl implements LocationDAO {

    private final JdbcTemplate jdbc;

    @Autowired
    public LocationDAOImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Location getLocationByID(int locationID) {
        try {
            final String GET_LOCATION_BY_ID = "SELECT * FROM Locations WHERE locationID = ?";
            return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), locationID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Location addNewLocation(Location location) {
        return null
    }

    @Override
    public List<Location> getAllLocations() {
        final String GET_ALL_LOCATIONS = "SELECT * FROM Locations";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public Boolean updateLocation(Location location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteLocation(int locationID) {
        final String SQL = "DELETE FROM Locations WHERE locationID";
        return jdbc.update(SQL, locationID) > 0;
    }

    @Override
    public List<Location> getLocationsBySuper(int superID) {
        // TODO Auto-generated method stub
        return null;
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
