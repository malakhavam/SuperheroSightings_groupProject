/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ss.dao;

import org.ss.models.Superpower;
import java.util.List;

/**
 *@author : Claude Seide, Everlyn Leon, Mariya Malakhava, Neyssa Cadet
 *
 */
public interface SuperpowerDao {
    
    Superpower getSuperpowerById(int id);
    
    List<Superpower> getAllSuperpowers();
    
    Superpower addSuperpower(Superpower superpower);
    
    void updateSuperpower(Superpower superpower);
    
    void deleteSuperpowerById(int id);
    
}
