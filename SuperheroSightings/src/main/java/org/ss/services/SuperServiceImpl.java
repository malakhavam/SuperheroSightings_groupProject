package org.ss.services;

import org.ss.DAO.SuperDAO;
import org.ss.DTO.Super;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ss.DTO.Location;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */

@Service
public class SuperServiceImpl implements SuperService {
        @Autowired
        SuperDAO superDAO;

        @Override
        public Super create(Super sup) {
            return superDAO.addNewSuper(sup);
        }

        @Override
        public List<Super> readAll() {
            return superDAO.getAllSupers();
        }

        @Override
        public Super readByID(int superID) {
            return superDAO.getSuperByID(superID);
        }

        
        @Override
        public List<Super> readSupersByLocation(Location locationID) {
            return superDAO.getSupersByLocation(locationID);
        }

        @Override
        public void update(Super sup) {
            superDAO.updateSuper(sup);
        }

        @Override
        public void delete(int superID) {
            superDAO.deleteSuper(superID);
        }

}
