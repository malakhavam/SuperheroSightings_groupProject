package org.ss.services;

import org.ss.DTO.Super;

import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */


public interface SuperService {

    public Super create(Super sup);

    public List<Super> readAll();

    public Super readByID(int superID);

    public List<Super> readSupersByLocation(int locationID);

    public void update(Super sup);

    public void delete(int superID);

}
