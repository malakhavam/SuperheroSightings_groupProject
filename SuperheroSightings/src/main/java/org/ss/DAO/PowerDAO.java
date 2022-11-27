package org.ss.DAO;

import org.ss.DTO.Power;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface PowerDAO {

    public Power getPowerByID(int powerID);

    public Power addNewPower(Power power);

    public List<Power> getAllPowers();

    public Boolean updatePower(Power power);

    public void deletePower(int powerID);
}
