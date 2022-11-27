package org.ss.DAO;

import org.ss.DTO.Location;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface LocationDAO {
    public Location getLocationByID(int locationID);

    public Location addNewLocation(Location location);

    public List<Location> getAllLocations();

    public void updateLocation(Location location);

    public void deleteLocation(int locationID);

    public List<Location> getLocationsBySuper(int superID);
}
