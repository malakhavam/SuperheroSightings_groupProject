package supersightings.Data;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ss.DAO.LocationDAO;
import org.ss.DTO.Location;

@SpringBootTest
public class LocationDaoTest {

    @Autowired 
    LocationDAO locationDao;

    public LocationDaoTest() {
    }

    @Before 
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocation(location.getLocationID());
        }
    }

    @Test
    public void testAddAndGetLocation() {
        Location location = new Location();
            location.getLocationName();
            location.getLocationDescription();
            location.getLocationAddress();
            location.getLocationLatitude();
            location.getLocationLongitude();
        location = locationDao.addNewLocation(location);
        
        Location fromDao = locationDao.getLocationByID(location.getLocationID());
        
        assertEquals(location, fromDao);
    }
    
}