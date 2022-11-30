package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.ss.DTO.Location;
import org.ss.DTO.Sighting;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */

@Repository
public class SightingDAOImpl implements SightingDAO{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingByID(int sightingID) {
        try {
            final String SQL = "SELECT * FROM Sightings WHERE sightingID = ?";
            Sighting sighting = jdbc.queryForObject(SQL, new SightingMapper(), sightingID);
            sighting.setSightingLocation(getLocationForSighting(sightingID));
            return sighting;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private Location getLocationForSighting(int sightingID){
        final String SQL = "SELECT l.* FROM Locations l "
                + "JOIN Sightings s ON s.locationID = l.locationID WHERE s.sightingID = ?";
        return jdbc.queryForObject(SQL, new LocationDAOImpl.LocationMapper(), sightingID);
    }

    @Override
    @Transactional
    public Sighting addNewSighting(Sighting sighting) {
        final String SQL = "INSERT INTO Sightings(superID, locationID, sightingDate) " +
                "VALUES(?,?,?)";
        jdbc.update(SQL,
                sighting.getSightingSuper().getSuperID(),
                sighting.getSightingLocation().getLocationID(),
                sighting.getSightingDate());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingID(newID);
        return sighting;
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SQL = "SELECT * Sightings";
        List<Sighting> sightings = jdbc.query(SQL, new SightingMapper());
        associateLocationsForSightings(sightings);
        return sightings;
    }
    void associateLocationsForSightings(List<Sighting> sightings){
        for (Sighting sighting : sightings) {
            sighting.setSightingLocation(getLocationForSighting(sighting.getSightingID()));
        }
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String SQL = "UPDATE Sightings SET superID = ?, locationID = ?, sightingDate = ? WHERE sighting ID = ?";
        jdbc.update(SQL,
                sighting.getSightingSuper().getSuperID(),
                sighting.getSightingLocation().getLocationID(),
                sighting.getSightingDate(),
                sighting.getSightingID());
    }

    @Override
    @Transactional
    public void deleteSighting(int sightingID) {
        final String SQL = "DELETE FROM Sightings WHERE sightingID = ?";
        jdbc.update(SQL, sightingID);
    }

    @Override
    public List<Sighting> getSightingsForLocation(Location location){
    final String SQL = "SELECT * FROM Sightings WHERE locationID = ?";
    List<Sighting> sighting = jdbc.query(SQL, new SightingMapper(), location.getLocationID());
    associateLocationsForSightings(sighting);
        return sighting;
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingID"));
            sighting.setSightingDate(rs.getDate("sightingDate").toLocalDate());

            return sighting;
        }
    }


}
