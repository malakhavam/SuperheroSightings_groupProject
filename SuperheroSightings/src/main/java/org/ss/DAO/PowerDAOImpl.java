package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.ss.DTO.Power;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class PowerDAOImpl implements PowerDAO {

    @Override
    public Power getPowerByID(int powerID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Power addNewPower(Power power) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Power> getAllPowers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updatePower(Power power) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deletePower(int powerID) {
        // TODO Auto-generated method stub
        return null;
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
