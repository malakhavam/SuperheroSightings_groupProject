package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Organization addNewOrganization(Organization organization) {
        final String SQL = "INSERT INTO Organization(organizationName, organizationDescription, OrganizationContact" +
            "VALUES(?,?,?)";
            jdbc.update(SQL,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationContact());

                int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
                organization.setOrganizationID(newID);
                return organization;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SQL = "SELECT * FROM Organizations";
        return jdbc.query(SQL, new OrganizationMapper());
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String SQL = "UPDATE Organizations SET organizationName = ?, organizationDescription = ?, organizationContact = ? WHERE organizationID = ?";
        jdbc.update(SQL,
            organization.getOrganizationName(),
            organization.getOrganizationDescription(),
            organization.getOrganizationContact());
    }

    @Override
    @Transactional
    public void deleteOrganization(int organizationID) {
        final String DELETE_ORGANIZATION = "DELETE FROM Organizations WHERE organizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, organizationID);

        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM SuperOrganizations WHERE organizationID = ?";
        jdbc.update(DELETE_SUPER_ORGANIZATION, organizationID);
    }

    @Override
    public List<Organization> getOrganizationsBySuper(int superID) {
        final String SQL = "SELECT Organizations.organizationID, Organizations.organizationName, Organizations.organizationDescription, Organizations.organizationContact"
        + "FROM SuperOrganizations"
        + "JOIN Organizations ON SuperOrganizations.organizationID = Organizations.organizationID"
        + "WHERE SuperOrganizations.superID = ?";
        return jdbc.query(SQL, new OrganizationMapper(), superID);
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
