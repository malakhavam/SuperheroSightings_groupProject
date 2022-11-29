package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.ss.DTO.Sighting;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class SightingDAOImpl implements SightingDAO{

    private final JdbcTemplate jdbc;

    @Autowired
    public SightingDAOImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Sighting getSightingByID(int sightingID) {
        try {
            final String SQL = "SELECT * FROM Sightings WHERE sightingID = ?";
            return jdbc.queryForObject(SQL, new SightingMapper(), sightingID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Sighting addNewSighting(Sighting sighting) {
        final String SQL = "INSERT INTO Sightings(superID, locationID, sightingDate)" +
            "VALUES(?,?,?)";
            jdbc.update(SQL,
            sighting.getSightingDate());
        
            int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            sighting.setSightingID(newID);
            return sighting;
        }

    @Override
    public List<Sighting> getAllSightings() {
        final String SQL = "SELECT * Sightings";
        return jdbc.query(SQL, new SightingMapper());
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String SQL = "UPDATE Sightings SET sightingDate = ? WHERE sighting ID = ?";
        jdbc.update(SQL, 
            sighting.getSightingDate());
    }

    @Override
    @Transactional
    public void deleteSighting(int sightingID) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        // TODO Auto-generated method stub
        return null;
    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingID(rs.getInt("sightingID"));
            sighting.setSightingDate(rs.getDate("sightingDate").toLocalDate());
                       
            return sighting;
        }
    }


}
