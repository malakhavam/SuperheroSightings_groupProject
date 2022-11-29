package supersightings.Data;

import static org.junit.Assert.assertEquals;

import java.util.List;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ss.DAO.LocationDAO;
import org.ss.DTO.Location;
import org.ss.TestApplicationConfiguration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class LocationDaoTest {

    @Autowired 
    LocationDAO locationDao;

    public LocationDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocation(location.getLocationID());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetLocation() {
        Location location = new Location();
            location.setLocationName("Test Location Name");
            location.setLocationDescription("Test Location Name");
            location.setLocationAddress("Test Location Name");
            location.setLocationLatitude("Test Location Name");
            location.setLocationLongitude("Test Location Name");
        location = locationDao.addNewLocation(location);
        
        Location fromDao = locationDao.getLocationByID(location.getLocationID());
        
        assertEquals(location, fromDao);
    }
    
}