package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.ss.DTO.Super;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class SuperDAOImpl implements SuperDAO{

    private final JdbcTemplate jdbc;

    @Autowired
    public SuperDAOImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Super getSuperByID(int superID) {
        try {
            final String SQL = "SELECT * FROM Supers WHERE superID = ?";
            return jdbc.queryForObject(SQL, new SuperMapper(), superID);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Super addNewSuper(Super sup) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Super> getAllSupers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateSuper(Super sup) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean deleteSuper(int superID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Super> getAllSupersByOrganization(int organizationID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Super> getSupersByLocation(int locationID) {
        // TODO Auto-generated method stub
        return null;
    }

    private static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int rowNum) throws SQLException {
            Super sup = new Super();
            sup.setSuperID(rs.getInt("superID"));
            sup.setSuperName(rs.getString("superName"));
            sup.setSuperDescription(rs.getString("superDescription"));
            
            return sup;
        }
    }


}
