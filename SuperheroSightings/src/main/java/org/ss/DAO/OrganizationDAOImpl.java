package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.ss.DTO.Organization;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class OrganizationDAOImpl implements OrganizationDAO{

    @Override
    public Organization getOrganizationByID(int organizationID) {
        // TODO Auto-generated method stub
        return null;
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
    public Boolean deleteOrganization(int organizationID) {
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
