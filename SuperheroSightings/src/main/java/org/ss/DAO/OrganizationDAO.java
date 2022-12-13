/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.models.Hero;
import org.ss.models.Organization;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
public interface OrganizationDao {
    
    Organization getOrganizationById(int id);
    
    List<Organization> getAllOrganizations();
    
    Organization addOrganization(Organization organization);
    
    void updateOrganization(Organization organization);
    
    void deleteOrganizationById(int id);
    
    List<Organization> getOrganizationsForHero(Hero hero);
}
