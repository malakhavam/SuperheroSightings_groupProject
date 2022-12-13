/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.models.Superpower;
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
public class SuperpowerDaoDB implements SuperpowerDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superpower getSuperpowerById(int id) {
        try {
            final String SELECT_SUPERPOWER_BY_ID = "SELECT * FROM Superpowers WHERE SuperpowerId = ?";
            return jdbc.queryForObject(SELECT_SUPERPOWER_BY_ID, new SuperpowerMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String SELECT_ALL_SUPERPOWERS = "SELECT * FROM Superpowers";
        return jdbc.query(SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superpower) {
        final String INSERT_SUPERPOWER = "INSERT INTO Superpowers(superpowerName, superpowerDescription) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SUPERPOWER,
                superpower.getName(),
                superpower.getDescription());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setId(newId);
        return superpower;
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE Superpowers SET superpowerName = ?, superpowerDescription = ?"
                + "WHERE SuperpowerId = ?";
        jdbc.update(UPDATE_SUPERPOWER,
                superpower.getName(),
                superpower.getDescription(),
                superpower.getId());
    }

    @Override
    @Transactional
    public void deleteSuperpowerById(int id) {
        final String DELETE_HERO_SUPERPOWER = "DELETE FROM HeroSuperpowers WHERE SuperpowerId = ?";
        jdbc.update(DELETE_HERO_SUPERPOWER, id);
        
        final String DELETE_SUPERPOWER = "DELETE FROM Superpowers WHERE SuperpowerId = ?";
        jdbc.update(DELETE_SUPERPOWER, id);
    }
    
    public static final class SuperpowerMapper implements RowMapper<Superpower> {

    @Override
    public Superpower mapRow(ResultSet rs, int index) throws SQLException {
        Superpower superpower = new Superpower();
        superpower.setId(rs.getInt("SuperpowerId"));
        superpower.setName(rs.getString("superpowerName"));
        superpower.setDescription(rs.getString("superpowerDescription"));
        return superpower;
    }
}
    
}
