

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.dao.HeroDaoDB.HeroMapper;
import org.ss.models.Hero;
import org.ss.models.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
@Repository
public class OrganizationDaoDB implements OrganizationDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Autowired
    HeroDaoDB heroDaoDB;

    @Override
    public Organization getOrganizationById(int id) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM Organizations WHERE OrganizationId = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
            organization.setMembers(getHerosForOrganization(id));
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    private List<Hero> getHerosForOrganization(int id) {
        final String SELECT_HEROES_FOR_ORGANIZATION = "SELECT h.* FROM Heroes h "
                + "JOIN HeroOrganizations ho ON h.HeroId = ho.HeroId WHERE ho.OrganizationId = ?";
        List<Hero> heros = jdbc.query(SELECT_HEROES_FOR_ORGANIZATION, new HeroMapper(), id);
        heroDaoDB.associateSuperpowersAndSightings(heros);
        return heros;
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM Organizations";
        List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
        associateHeros(organizations);
        return organizations;
    }
    
    private void associateHeros(List<Organization> organizations) {
        for (Organization organization : organizations) {
            organization.setMembers(getHerosForOrganization(organization.getId()));
        }
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO Organizations(organizationName,IsHero,organizationDescription,organizationAddress,organizationContact) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION,
                organization.getName(),
                organization.isIsHero(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        insertHeroOrganization(organization);
        return organization;
    }
    
    private void insertHeroOrganization(Organization organization){
        final String INSERT_HERO_ORGANIZATION = "INSERT INTO "
                + "HeroOrganizations(HeroId, OrganizationId) VALUES(?,?)";
        for(Hero hero : organization.getMembers()) {
            jdbc.update(INSERT_HERO_ORGANIZATION, 
                    hero.getId(),
                    organization.getId());
        }
    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE Organizations SET organizationName = ?, IsHero = ?, organizationDescription = ?, organizationAddress = ?, organizationContact = ?"
                + "WHERE OrganizationId = ?";
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getName(),
                organization.isIsHero(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getContact(),
                organization.getId());
        
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganizations WHERE OrganizationId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, organization.getId());
        insertHeroOrganization(organization);
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM HeroOrganizations WHERE OrganizationId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, id);
        
        final String DELETE_ORGANIZATION = "DELETE FROM Organizations WHERE OrganizationId = ?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }

    @Override
    public List<Organization> getOrganizationsForHero(Hero hero) {
        final String SELECT_ORGANIZATIONS_FOR_HERO = "SELECT o.* FROM Organizations o JOIN "
                + "HeroOrganizations ho ON ho.OrganizationId = o.OrganizationId WHERE ho.HeroId = ?";
        List<Organization> organizations = jdbc.query(SELECT_ORGANIZATIONS_FOR_HERO, 
                new OrganizationMapper(), hero.getId());
        associateHeros(organizations);
        return organizations;
    }
    
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("OrganizationId"));
            organization.setName(rs.getString("organizationName"));
            organization.setIsHero(rs.getBoolean("isHero"));
            organization.setDescription(rs.getString("organizationDescription"));
            organization.setAddress(rs.getString("organizationAddress"));
            organization.setContact(rs.getString("organizationContact"));
            return organization;
        }
    }
    
}
