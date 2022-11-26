package org.ss.services;

import org.ss.DTO.Location;
import java.util.List;


/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface LocationService {

    public Location create(Location location);

    public List<Location> readAll();

    public Location readByID(int locationID);

    public List<Location> readLocationsBySuper(int superID);

    public void update(Location location);

    public void delete(int locationID);
}
