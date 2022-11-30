package supersightings.Data;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ss.DAO.*;
import org.ss.DTO.*;
import org.ss.TestApplicationConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class PowerDaoTest {
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

    public PowerDaoTest() {
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
    public void testAddAndGetPower(){
        Power power = new Power();
        power.setPowerName("Test Power Name");
        power.setPowerDescription("Test Power Description");

        power = powerDao.addNewPower(power);
        Power fromDao = powerDao.getPowerByID(power.getPowerID());

        assertEquals(power, fromDao);
    }

    @Test
    public void testGetAllPowers(){
        Power power = new Power();
        power.setPowerName("Test Power Name");
        power.setPowerDescription("Test Power Description");
        power = powerDao.addNewPower(power);

        Power power2 = new Power();
        power2.setPowerName("Test Power Name");
        power2.setPowerDescription("Test Power Description");
        power2 = powerDao.addNewPower(power2);

        List<Power> powers = powerDao.getAllPowers();

        assertEquals(2, powers.size());
        assertTrue(powers.contains(power));
        assertTrue(powers.contains(power2));
    }
}
