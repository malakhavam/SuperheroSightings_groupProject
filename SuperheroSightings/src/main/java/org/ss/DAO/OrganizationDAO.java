package org.ss.DAO;

import org.ss.DTO.Organization;
import java.util.List;


/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface OrganizationDAO {
    public Organization getOrganizationByID(int organizationID);
    public Organization addNewOrganization(Organization organization);

    public List<Organization> getAllOrganizations();

    public Boolean updateOrganization(Organization organization);

    public Boolean deleteOrganization(int organizationID);

    public List<Organization> getOrganizationsBySuper(int superID);
}
