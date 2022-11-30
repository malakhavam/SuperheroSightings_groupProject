package supersightings.Data;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        location.setLocationDescription("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLocationLatitude("Test Location Latitude");
        location.setLocationLongitude("Test Location Longitude");
        location = locationDao.addNewLocation(location);

        Location fromDao = locationDao.getLocationByID(location.getLocationID());

        assertEquals(location, fromDao);
    }

    @Test
    public void testGetAllLocations() {
        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDescription("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLocationLatitude("Test Location Latitude");
        location.setLocationLongitude("Test Location Longitude");
        location = locationDao.addNewLocation(location);

        Location location2 = new Location();
        location2.setLocationName("Test Location Name");
        location2.setLocationDescription("Test Location Description");
        location2.setLocationAddress("Test Location Address");
        location2.setLocationLatitude("Test Location Latitude");
        location2.setLocationLongitude("Test Location Longitude");
        location2 = locationDao.addNewLocation(location);

        List<Location> teachers = locationDao.getAllLocations();

        assertEquals(2, teachers.size());
        assertTrue(teachers.contains(location));
        assertTrue(teachers.contains(location2));
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDescription("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLocationLatitude("Test Location Latitude");
        location.setLocationLongitude("Test Location Longitude");
        location = locationDao.addNewLocation(location);

        Location fromDao = locationDao.getLocationByID(location.getLocationID());
        assertEquals(location, fromDao);

        location.setLocationName("New Test First");
        locationDao.updateLocation(location);

        assertNotEquals(location, fromDao);

        fromDao = locationDao.getLocationByID(location.getLocationID());

        assertEquals(location, fromDao);
    }

}