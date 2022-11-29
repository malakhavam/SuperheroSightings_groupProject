package org.ss.services;

import org.ss.DTO.Location;
import org.ss.DTO.Super;

import java.util.List;


/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface LocationService {

    public Location create(Location location);

    public List<Location> readAll();

    public Location readByID(int locationID);

    public List<Location> readLocationsBySuper(Super sup);

    public void update(Location location);

    public void delete(int locationID);
}
