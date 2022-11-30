package org.ss.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.ss.DTO.Organization;
import org.ss.DTO.Super;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */

@Repository
public class OrganizationDAOImpl implements OrganizationDAO{

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    SuperDAOImpl supDao;

    @Override
    public Organization getOrganizationByID(int organizationID) {
        try{
            final String SQL = "SELECT * FROM Organizations WHERE organizationID = ?";
            Organization org = jdbc.queryForObject(SQL, new OrganizationMapper(), organizationID);
            return org;
        } catch(DataAccessException ex){
            return null;
        }
    }
    private List<Super> getSupersForOrganization(int id) {
        final String SQL = "SELECT s.* FROM Supers s "
                + "JOIN Organizations o ON s.superID = o.superID WHERE o.OrganizationID = ?";
        List<Super> sup = jdbc.query(SQL, new SuperDAOImpl.SuperMapper(), id);
        supDao.associatePowersAndSightings(sup);
        return sup;}

    @Override
    @Transactional
    public Organization addNewOrganization(Organization organization) {
        final String SQL = "INSERT INTO Organization(organizationName, organizationDescription, OrganizationContact) "
                + "VALUES(?,?,?)";
        jdbc.update(SQL,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationContact());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationID(newID);
        insertSuperOrganization(organization);
        return organization;
    }
    private void insertSuperOrganization(Organization organization){
        final String SQL = "INSERT INTO SuperOrganization(superID, organizationId) VALUES(?,?)";
        for(Super sup : organization.getSupers()) {
            jdbc.update(SQL,
                    sup.getSuperID(),
                    organization.getOrganizationID());
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SQL = "SELECT * FROM Organizations";
        List<Organization> organizations = jdbc.query(SQL, new OrganizationMapper());
        associateSupers(organizations);
        return organizations;
    }
    private void associateSupers(List<Organization> organizations) {
            for (Organization organization : organizations) {
                organization.setSupers(getSupersForOrganization(organization.getOrganizationID()));
            }
        }

    @Override
    public void updateOrganization(Organization organization) {
        final String SQL = "UPDATE Organizations SET organizationName = ?, organizationDescription = ?, organizationContact = ? WHERE organizationID = ?";
        jdbc.update(SQL,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationContact(),
                organization.getOrganizationID());

        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganization WHERE OrganizationId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, organization.getOrganizationID());
        insertSuperOrganization(organization);
    }

    @Override
    @Transactional
    public void deleteOrganization(int organizationID) {
        final String DELETE_SUPER_ORGANIZATION = "DELETE FROM SuperOrganizations WHERE organizationID = ?";
        jdbc.update(DELETE_SUPER_ORGANIZATION, organizationID);

        final String DELETE_ORGANIZATION = "DELETE FROM Organizations WHERE organizationID = ?";
        jdbc.update(DELETE_ORGANIZATION, organizationID);
    }

    @Override
    public List<Organization> getOrganizationsBySuper(int superID) {
        final String SQL = "SELECT Organizations.organizationID, Organizations.organizationName, Organizations.organizationDescription, Organizations.organizationContact"
                + "FROM SuperOrganizations"
                + "JOIN Organizations ON SuperOrganizations.organizationID = Organizations.organizationID"
                + "WHERE SuperOrganizations.superID = ?";
        List<Organization> organizations = jdbc.query(SQL, new OrganizationMapper(), superID);
        associateSupers(organizations);
        return organizations;
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
