package org.ss.services;

import org.ss.DTO.Super;

import java.util.List;
import org.ss.DTO.Location;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface SuperService {

    public Super create(Super sup);

    public List<Super> readAll();

    public Super readByID(int superID);

    public List<Super> readSupersByLocation(Location locationID);

    public void update(Super sup);

    public void delete(int superID);

}
