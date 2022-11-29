package org.ss.services;

import org.ss.DAO.PowerDAO;
import org.ss.DTO.Power;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class PowerServiceImpl implements PowerService{

    @Autowired
    PowerDAO powerDAO;

    @Override
    public Power create(Power power) {
        return powerDAO.addNewPower(power);
    }

    @Override
    public List<Power> readAll() {
        return powerDAO.getAllPowers();
    }

    @Override
    public Power readByID(int powerID) {
        return powerDAO.getPowerByID(powerID);
    }

    @Override
    public void update(Power power) {
        powerDAO.updatePower(power);
    }

    @Override
    public void delete(int powerID) {
        powerDAO.deletePower(powerID);
    }


}
