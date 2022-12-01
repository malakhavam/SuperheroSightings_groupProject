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
import org.ss.DTO.*;

/**
 * @author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 */

@Repository
public class SuperDAOImpl implements SuperDAO {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    SightingDAOImpl sightingDao;

    @Override
    public Super getSuperByID(int superID) {
        try {
            final String SQL = "SELECT * FROM Supers WHERE superID = ?";
            Super sup = jdbc.queryForObject(SQL, new SuperMapper(), superID);
            sup.setPowers(getSuperpowersForSuper(superID));
            sup.setSightings(getSightingsForSuper(superID));
            return sup;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private List<Power> getSuperpowersForSuper(int id) {
        final String SQL = "SELECT p.* FROM Powers p "
                + "JOIN Superpowers sp ON sp.powerID = p.powerID WHERE sp.superID = ?";
        return jdbc.query(SQL, new PowerDAOImpl.PowerMapper(), id);
    }

    private List<Sighting> getSightingsForSuper(int id) {
        final String SQL = "SELECT * FROM Sightings WHERE superID = ?";
        List<Sighting> sightings = jdbc.query(SQL, new SightingDAOImpl.SightingMapper(), id);
        sightingDao.associateLocationsForSightings(sightings);
        return sightings;
    }

    @Override
    @Transactional
    public Super addNewSuper(Super sup) {
        final String SQL = "INSERT INTO Supers(superName, superDescription) " +
                "VALUES(?,?)";
        jdbc.update(SQL,
                sup.getName(),
                sup.getDescription());

        int newID = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sup.setId(newID);
        insertSuperPower(sup);
        insertSighting(sup);
        return sup;
    }
    
     @Override
    public Super getSuperForSighting(Sighting sighting) {
        final String SQL = "SELECT h.* FROM Supers h JOIN "
                + "Sighting s ON s.superID = h.superID WHERE s.sightingID = ?";
        Super hero = jdbc.queryForObject(SQL, 
                new SuperMapper(), sighting.getSightingID());
        hero.setPowers(getSuperpowersForSuper(hero.getId()));
        hero.setSightings(getSightingsForSuper(hero.getId()));
        return hero;
    }

    private void insertSuperPower(Super sup) {
        final String SQL = "INSERT INTO Superpower(superID, powerID) VALUES(?,?)";
        for (Power power : sup.getPowers()) {
            jdbc.update(SQL,
                    sup.getId(),
                    power.getPowerID());
        }
    }

    private void insertSighting(Super sup) {
        final String SQL = "INSERT INTO "
                + "Sighting(SuperId, LocationId, Date) VALUES(?,?,?)";
        for (Sighting sighting : sup.getSightings()) {
            jdbc.update(SQL,
                    sup.getId(),
                    sighting.getSightingLocation().getLocationID(),
                    sighting.getSightingDate());
            int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            sighting.setSightingID(newId);
        }
    }

        @Override
        public List<Super> getAllSupers() {
            final String SQL = "SELECT * FROM Supers";
            List<Super> sups = jdbc.query(SQL, new SuperMapper());
            associatePowersAndSightings(sups);
            return sups;
        }
        public void associatePowersAndSightings(List<Super> sups) {
            for (Super sup : sups) {
                sup.setPowers(getSuperpowersForSuper(sup.getId()));
                sup.setSightings(getSightingsForSuper(sup.getId()));
            }
        }

        @Override
        public void updateSuper (Super sup){
            final String SQL = "UPDATE Supers SET superName = ?, superDescription = ? WHERE superID = ?";
            jdbc.update(SQL,
                    sup.getName(),
                    sup.getDescription(),
                    sup.getId());

            final String DELETE_SUPERPOWER = "DELETE FROM Superpowers WHERE superID = ?";
            jdbc.update(DELETE_SUPERPOWER, sup.getId());
            insertSuperPower(sup);

            final String DELETE_SIGHTING = "DELETE FROM Sightings WHERE superID = ?";
            jdbc.update(DELETE_SIGHTING, sup.getId());
            insertSighting(sup);
        }

        @Override
        @Transactional
        public void deleteSuper ( int superID){
            final String DELETE_SUPER_POWER = "DELETE FROM Superpowers WHERE superID = ?";
            jdbc.update(DELETE_SUPER_POWER, superID);

            final String DELETE_SUPER_ORGANIZATION = "DELETE FROM SuperOrganizations WHERE superID = ?";
            jdbc.update(DELETE_SUPER_ORGANIZATION, superID);

            final String DELETE_SUPER_SIGHTING = "DELETE FROM SuperSighting WHERE superID = ?";
            jdbc.update(DELETE_SUPER_SIGHTING, superID);

            final String DELETE_SUPER = "DELETE FROM Supers WHERE superID = ?";
            jdbc.update(DELETE_SUPER, superID);
        }

        @Override
        public List<Super> getAllSupersByOrganization ( int organizationID){
            final String SQL = "SELECT s.* FROM Supers s "
                    + "JOIN Organizations o ON o.superID = s.superID "
                    + "WHERE o.organizationID = ?";
            List<Super> sups = jdbc.query(SQL, new SuperMapper(), organizationID);
            associatePowersAndSightings(sups);
            return sups;
        }

        @Override
        public List<Super> getSupersByLocation ( int locationID){
            final String SQL = "SELECT sup.* FROM Supers sup "
                    + "JOIN Sighting s ON s.superID = sup.superID "
                    + "WHERE s.locationID = ?";
            List<Super> sups = jdbc.query(SQL, new SuperMapper(), locationID);
            associatePowersAndSightings(sups);
            return sups;
        }
        
         

        public static final class SuperMapper implements RowMapper<Super> {

            @Override
            public Super mapRow(ResultSet rs, int rowNum) throws SQLException {
                Super sup = new Super();
                sup.setId(rs.getInt("superID"));
                sup.setName(rs.getString("superName"));
                sup.setDescription(rs.getString("superDescription"));

                return sup;
            }
        }}



