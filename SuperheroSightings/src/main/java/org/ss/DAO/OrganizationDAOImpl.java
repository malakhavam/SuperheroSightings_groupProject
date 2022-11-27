package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.ss.DTO.Organization;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class OrganizationDAOImpl implements OrganizationDAO{
    
    private final JdbcTemplate jdbc;

    @Autowired
    public OrganizationDAOImpl(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Organization getOrganizationByID(int organizationID) {
        try{
            final String SQL = "SELECT * FROM Organizations WHERE organizationID = ?";
            return jdbc.queryForObject(SQL, new OrganizationMapper(), organizationID);
        } catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    public Organization addNewOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateOrganization(Organization organization) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteOrganization(int organizationID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Organization> getOrganizationsBySuper(int superID) {
        // TODO Auto-generated method stub
        return null;
    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationID(rs.getInt("organizationID"));
            organization.setOrganizationName(rs.getString("organizationName"));
            organization.setOrganizationDescription(rs.getString("organizationDescription"));
            organization.setOrganizationContact(rs.getString("organizationContact"));
            return organization;
        }
    }


}
