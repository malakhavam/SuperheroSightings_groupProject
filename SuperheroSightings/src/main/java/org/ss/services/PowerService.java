package org.ss.services;

import org.ss.DTO.Power;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface PowerService {

    public Power create(Power power);

    public List<Power> readAll();

    public Power readByID(int powerID);

    public void update(Power power);

    public void delete(int powerID);

}
