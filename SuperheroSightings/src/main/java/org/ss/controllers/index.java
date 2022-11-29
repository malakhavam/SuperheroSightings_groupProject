package org.ss.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.ss.DAO.*;

public class index {
    private final LocationDAO locationDao;
    private final OrganizationDAO organizationDao;
    private final PowerDAO powerDao;
    private final SightingDAO sightingDao;
    private final SuperDAO superDao;

    public index(LocationDAO locationDao, OrganizationDAO organizationDao, PowerDAO powerDao,
                 SightingDAO sightingDao, SuperDAO superDao) {
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
        this.powerDao = powerDao;
        this.sightingDao = sightingDao;
        this.superDao = superDao;

    }

//pushing testtt


}
