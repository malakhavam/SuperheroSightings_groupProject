package supersightings.Data;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ss.DAO.*;
import org.ss.DTO.*;
import org.ss.TestApplicationConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class SuperDaoTest {
    @Autowired
    SuperDAO superDao;

    @Autowired
    LocationDAO locationDao;

    @Autowired
    OrganizationDAO organizationDao;

    @Autowired
    SightingDAO sightingDao;

    @Autowired
    PowerDAO powerDao;

    public SuperDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        List<Super> sups = superDao.getAllSupers();
        for(Super sup : sups){
            superDao.deleteSuper(sup.getSuperID());
        }

        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations) {
            locationDao.deleteLocation(location.getLocationID());
        }

        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations){
            organizationDao.deleteOrganization(organization.getOrganizationID());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings) {
            sightingDao.deleteSighting(sighting.getSightingID());
        }

        List<Power> powers = powerDao.getAllPowers();
        for(Power power : powers){
            powerDao.deletePower(power.getPowerID());
        }
    }

    @AfterEach
    public void tearDown() {
    }


    @Test
    public void testAddAndGetSuper(){
        Power power = new Power();
        power.setPowerName("Test Power Name");
        power.setPowerDescription("Test Power Description");
        power = powerDao.addNewPower(power);

        List<Power> powers = new ArrayList<>();
        powers.add(power);
        List<Sighting> sightings = new ArrayList<>();

        Location location = new Location();
        location.setLocationName("Test Location Name");
        location.setLocationDescription("Test Location Description");
        location.setLocationAddress("Test Location Address");
        location.setLocationLatitude("Test Location Latitude");
        location.setLocationLongitude("Test Location Longitude");
        location = locationDao.addNewLocation(location);

        Super sup = new Super();
        sup.setSuperName("Test Super Name 2");
        sup.setSuperDescription("Test Super Description 2");
        sup.setPowers(powers);
        sup.setSightings(sightings);
        sup = superDao.addNewSuper(sup);

        LocalDate date = LocalDate.now();

        Sighting sighting = new Sighting();
        sighting.setSightingSuper(sup);
        sighting.setSightingLocation(location);
        sighting.setSightingDate(date);
        sighting = sightingDao.addNewSighting(sighting);

        sightings.add(sighting);

        sup = superDao.addNewSuper(sup);
        Super fromDao = superDao.getSuperByID(sup.getSuperID());

        assertEquals(sup, fromDao);
    }
    
}
