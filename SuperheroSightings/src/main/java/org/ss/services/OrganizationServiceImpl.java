package org.ss.services;

import org.ss.DAO.OrganizationDAO;
import org.ss.DTO.Organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    OrganizationDAO organizationDAO;

    @Override
    public Organization create(Organization organization) {
        return organizationDAO.addNewOrganization(organization);
    }

    @Override
    public List<Organization> readAll() {
        return organizationDAO.getAllOrganizations();
    }

    @Override
    public Organization readByID(int organizationID) {
        return organizationDAO.getOrganizationByID(organizationID);
    }

    @Override
    public List<Organization> readAllBySuper(int superID) {
        return organizationDAO.getOrganizationsBySuper(superID);
    }

    @Override
    public void update(Organization organization) {
        organizationDAO.updateOrganization(organization);
    }

    @Override
    public void delete(int organizationID) {
        organizationDAO.deleteOrganization(organizationID);
    }


}
