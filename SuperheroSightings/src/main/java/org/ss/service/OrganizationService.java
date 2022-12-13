/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.service;

import org.ss.dao.HeroDao;
import org.ss.dao.LocationDao;
import org.ss.dao.OrganizationDao;
import org.ss.dao.SightingDao;
import org.ss.dao.SuperpowerDao;
import org.ss.models.Hero;
import org.ss.models.Organization;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
@Service
public class OrganizationService {
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    // SERVICE FUNCTION
    public Organization createOrganization(String name, boolean isHero, String description, String address, String contact, List<Hero> heros){
        Organization organization = new Organization();
        organization.setName(name);
        organization.setIsHero(isHero);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setContact(contact);
        organization.setMembers(heros);

        return organization;
    }
    
    
    // EXTERNAL DAO FUNCTIONS  

    // LOCAL DAO FUNCTIONS  
    public Organization getOrganizationById(int id){
        return organizationDao.getOrganizationById(id);      
    }
    public List<Organization> getAllOrganizations(){
        return organizationDao.getAllOrganizations();
    }
    public Organization addOrganization(Organization organization){
        return organizationDao.addOrganization(organization);
    }
    public void updateOrganization(Organization organization){
        organizationDao.updateOrganization(organization);
    }
    public void deleteOrganizationById(int id){
        organizationDao.deleteOrganizationById(id);
    }
}
