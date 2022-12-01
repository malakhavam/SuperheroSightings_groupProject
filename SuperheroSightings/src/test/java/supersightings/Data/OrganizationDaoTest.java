package supersightings.Data;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ss.DAO.*;
import org.ss.DTO.*;
import org.ss.TestApplicationConfiguration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class OrganizationDaoTest {
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

    public OrganizationDaoTest() {
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
    public void testAddAndGetOrganization(){


        Organization organization = new Organization();
        organization.setOrganizationName("Test Organization Name");
        organization.setOrganizationDescription("Test Organization Description");
        organization.setOrganizationContact("Test Organization Contact");

        organization = organizationDao.addNewOrganization(organization);
        Organization fromDao = organizationDao.getOrganizationByID(organization.getOrganizationID());
        assertEquals(organization, fromDao);
    }
}
