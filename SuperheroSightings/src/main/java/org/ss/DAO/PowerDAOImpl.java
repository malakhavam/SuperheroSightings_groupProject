package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.ss.DTO.Power;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class PowerDAOImpl implements PowerDAO {

    private final JdbcTemplate jdbc;

    @Autowired
    public PowerDAOImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Power getPowerByID(int powerID) {
        try {
            final String SQL = "SELECT * FROM Powers WHERE powerID = ?";
            return jdbc.queryForObject(SQL, new PowerMapper(), powerID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Power addNewPower(Power power) {
        final String SQL = "INSERT INTO Powers(powerName, powerDescription)" +
            "VALUES(?,?)";
            jdbc.update(SQL,
            power.getPowerName(),
            power.getPowerDescription());

            int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            power.setPowerID(newID);
            return power;
    }

    @Override
    public List<Power> getAllPowers() {
        final String SQL = "SELECT * FROM Powers";
        return jdbc.query(SQL, new PowerMapper());
    }

    @Override
    public Boolean updatePower(Power power) {
        final String SQL = "UPDATE Powers SET powerName = ?, powerDescription = ? WHERE powerID = ?";
        return jdbc.update(SQL, 
            power.getPowerName(),
            power.getPowerDescription())>0;
    }

    @Override
    public void deletePower(int powerID) {
        final String DELETE_POWER = "DELETE FROM Powers WHERE powerID =?";
        jdbc.update(DELETE_POWER, powerID);

        final String DELETE_SUPERPOWERS = "DELETE FROM Superpowers WHERE powerID = ?";
        jdbc.update(DELETE_SUPERPOWERS, powerID);
    }

    private static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int rowNum) throws SQLException {
            Power power = new Power();
            power.setPowerID(rs.getInt("powerID"));
            power.setPowerName(rs.getString("powerName"));
            power.setPowerDescription(rs.getString("powerDescription"));
            
            return power;
        }
    }


}
