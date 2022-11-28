package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
import org.ss.DTO.Sighting;
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
    @Transactional
    public Super addNewSuper(Super sup) {
        final String SQL = "INSERT INTO Supers(superName, superDescription)" + 
            "VALUES(?,?)";
            jdbc.update(SQL, 
            sup.getSuperName(),
            sup.getSuperDescription());

            int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            sup.setSuperID(newID);
            return sup;
    }

    @Override
    public List<Super> getAllSupers() {
        final String SQL = "SELECT * FROM Supers";
        return jdbc.query(SQL, new SuperMapper());
    }

    @Override
    public void updateSuper(Super sup) {
        final String SQL = "UPDATE Supers SET superName = ?, superDescription = ? WHERE superID = ?";
        jdbc.update(SQL, 
        sup.getSuperName(),
        sup.getSuperDescription());
    }

    @Override
    @Transactional
    public void deleteSuper(int superID) {
        // TODO Auto-generated method stub
        
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
