package org.ss.services;

import org.ss.DTO.Organization;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface OrganizationService {

    public Organization create(Organization organization);

    public List<Organization> readAll();

    public Organization readByID(int organizationID);

    public List<Organization> readAllBySuper(int superID);

    public void update(Organization organization);

    public void delete(int organizationID);
}
