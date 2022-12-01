package org.ss.DAO;
import org.ss.DTO.Super;
import java.util.List;
import org.ss.DTO.Location;
import org.ss.DTO.Sighting;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface SuperDAO {

    public Super getSuperByID(int superID);

    public Super addNewSuper(Super sup);

    public List<Super> getAllSupers();

    public void updateSuper(Super sup);

    public void deleteSuper(int superID);

    public List<Super> getAllSupersByOrganization(int organizationID);

    public List<Super> getSupersByLocation(int locationID);
    
    Super getSuperForSighting(Sighting sighting);
    
}
