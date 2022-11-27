package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
            return jdbc.queryForObject(SQL, new LocationMapper(), sightingID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Sighting addNewSighting(Sighting sighting) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Sighting> getAllSightings() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateSighting(Sighting sighting) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteSighting(int sightingID) {
        // TODO Auto-generated method stub
        return false;
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
