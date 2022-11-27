package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.ss.DTO.Super;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class SuperDAOImpl implements SuperDAO{

    @Override
    public Super getSuperByID(int superID) {
        // TODO Auto-generated method stub
        return null;
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

    /*private static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int rowNum) throws SQLException {
            Super sup = new Super();
            sup.setSuperID(rs.getInt("powerID"));
            sup.setSuperName(rs.getString("powerName"));
            sup.setSuperDescription(rs.getString("superDescription"));
            
            return sup;
        }
    }*/


}
